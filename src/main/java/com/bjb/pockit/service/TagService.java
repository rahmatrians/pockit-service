package com.bjb.pockit.service;

import com.bjb.pockit.dto.ApiResponse;
import com.bjb.pockit.dto.ResTagDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TagService {
    @Transactional
    public ApiResponse<List<ResTagDTO>> getTag() {
        String errMessage = "Success"; // Default success message
        List<ResTagDTO> response = new ArrayList<>();

        try {
            // Membuat data statis sesuai permintaan
            response.add(ResTagDTO.builder().id(Long.valueOf(1)).name("Makan").build());
            response.add(ResTagDTO.builder().id(Long.valueOf(2)).name("Hiburan").build());
            response.add(ResTagDTO.builder().id(Long.valueOf(3)).name("Transport").build());
            response.add(ResTagDTO.builder().id(Long.valueOf(4)).name("Lainnya").build());

        } catch (Exception e) {
            errMessage = e.getMessage(); // Pastikan pesan error tersimpan
            response = null;
            log.error("Error : {}", e.getMessage(), e); // Perbaiki logging
        }

        return ApiResponse.<List<ResTagDTO>>builder()
                .data(response)
                .message(errMessage)
                .build();
    }
}
