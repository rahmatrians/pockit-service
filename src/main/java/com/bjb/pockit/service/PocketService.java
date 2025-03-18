package com.bjb.pockit.service;

import com.bjb.pockit.dto.ApiResponse;
import com.bjb.pockit.dto.ResPocketTypeDTO;
import com.bjb.pockit.entity.PocketType;
import com.bjb.pockit.repository.PocketTypeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PocketService {
    @Autowired
    private PocketTypeRepository pocketTypeRepository;

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
}
