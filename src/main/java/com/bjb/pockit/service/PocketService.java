package com.bjb.pockit.service;

import com.bjb.pockit.dto.*;
import com.bjb.pockit.entity.Pocket;
import com.bjb.pockit.entity.PocketType;
import com.bjb.pockit.repository.PocketRepository;
import com.bjb.pockit.repository.PocketTypeRepository;
import com.bjb.pockit.util.StringUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PocketService {
    @Autowired
    private PocketTypeRepository pocketTypeRepository;

    @Autowired
    private PocketRepository pocketRepository;

    @Transactional
    public ApiResponse<List<ResPocketTypeDTO>> getPocketType() {
        String errMessage = "Success"; // Default success message
        List<ResPocketTypeDTO> response = new ArrayList<>();

        try {
            List<PocketType> data = pocketTypeRepository.findAll();
            if (ObjectUtils.isEmpty(data)) {
                errMessage = "No data found"; // Perbaiki pesan error agar lebih sesuai
                throw new Exception(errMessage);
            }

            // Mapping dari entity ke DTO
            response = data.stream()
                    .map(pocketType -> ResPocketTypeDTO.builder()
                            .id(pocketType.getId())
                            .name(pocketType.getName())
                            .build())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            errMessage = e.getMessage(); // Pastikan pesan error tersimpan
            response = null;
            log.error("Error : {}", e.getMessage(), e); // Perbaiki logging
        }

        return ApiResponse.<List<ResPocketTypeDTO>>builder()
                .data(response)
                .message(errMessage)
                .build();
    }

    @Transactional
    public ApiResponse<ResListPocketDTO> getListPocket(Long userId, Long page, Long size){
        String message = "";
        ResListPocketDTO response = new ResListPocketDTO();

        try {
            int defaultPage = Math.toIntExact((page != null) ? page : 0);
            int defaultSize = Math.toIntExact((size != null) ? size : 10);
            Pageable pageable = PageRequest.of(defaultPage, defaultSize);

            Page<Pocket> listPocket = pocketRepository.findListPocketByUserId(userId, pageable);

            if (org.apache.commons.lang3.ObjectUtils.isEmpty(listPocket)) {
                message = "Failed to get list pocket";
                throw  new Exception(message);
            }


            List<ListPocketDTO> pocketList = listPocket.getContent()
                    .stream()
                    .map(pocket -> ListPocketDTO.builder()
                            .id(pocket.getId())
                            .name(pocket.getName())
                            .account_number(pocket.getAccountNumber())
                            .balance(pocket.getBalance())
                            .build()
                    ).toList();

            response = ResListPocketDTO.builder()
                    .userId(userId)
                    .size(Long.valueOf(listPocket.getSize()))
                    .page(Long.valueOf(listPocket.getTotalPages()))
                    .pockets(pocketList)
                    .build();

            message = "list pocket get successfully";

        }catch (Exception e) {
            response = null;
            message = e.getMessage();
            log.error("Error : {}", e.getMessage(), e);
        }

        return ApiResponse.<ResListPocketDTO>builder()
                .data(response)
                .message(message)
                .build();
    }
}
