package com.bjb.pockit.service;

import com.bjb.pockit.dto.ApiResponse;
import com.bjb.pockit.dto.ResCategoryDTO;
import com.bjb.pockit.dto.ResPocketTypeDTO;
import com.bjb.pockit.dto.ResTagDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CategoryService {
    @Transactional
    public ApiResponse<List<ResCategoryDTO>> getCat() {
        String errMessage = "Success"; // Default success message
        List<ResCategoryDTO> response = new ArrayList<>();

        try {
            // Membuat data statis sesuai permintaan
            response.add(ResCategoryDTO.builder().id(Long.valueOf(1)).name("Pengeluaran").build());
            response.add(ResCategoryDTO.builder().id(Long.valueOf(2)).name("Pemasukan").build());
            response.add(ResCategoryDTO.builder().id(Long.valueOf(3)).name("Pemasukan Split Bill").build());
            response.add(ResCategoryDTO.builder().id(Long.valueOf(4)).name("Pengeluaran Split Bill").build());

        } catch (Exception e) {
            errMessage = e.getMessage(); // Pastikan pesan error tersimpan
            response = null;
            log.error("Error : {}", e.getMessage(), e); // Perbaiki logging
        }

        return ApiResponse.<List<ResCategoryDTO>>builder()
                .data(response)
                .message(errMessage)
                .build();
    }
}