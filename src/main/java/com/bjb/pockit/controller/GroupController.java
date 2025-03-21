package com.bjb.pockit.controller;

import com.bjb.pockit.constant.ResponseStatus;
import com.bjb.pockit.dto.ApiResponse;
import com.bjb.pockit.dto.ResCategoryDTO;
import com.bjb.pockit.dto.ResPocketTypeDTO;
import com.bjb.pockit.dto.ResTagDTO;
import com.bjb.pockit.service.CategoryService;
import com.bjb.pockit.service.PocketService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GroupController {

    @Autowired
    private PocketService pocketTypeService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/pockettype")
    public ResponseEntity<ApiResponse<List<ResPocketTypeDTO>>> getPocketType(){
        ApiResponse<List<ResPocketTypeDTO>> response = pocketTypeService.getPocketType();
        if (ObjectUtils.isNotEmpty(response.getData())) {
            return ResponseEntity
                    .status(ResponseStatus.OK.getStatus())
                    .body(ApiResponse.success(response.getData(), response.getMessage()));
        } else {
            return ResponseEntity
                    .status(ResponseStatus.NOT_FOUND.getStatus())
                    .body(ApiResponse.error(response.getMessage()));
        }
    }

    @GetMapping("/tag")
    public ResponseEntity<ApiResponse<List<ResTagDTO>>> getTag(){
        List<ResTagDTO> tagTypes = new ArrayList<>();

        // Membuat data statis langsung di controller
        tagTypes.add(ResTagDTO.builder().id(Long.valueOf(1)).name("Makan").build());
        tagTypes.add(ResTagDTO.builder().id(Long.valueOf(2)).name("Hiburan").build());
        tagTypes.add(ResTagDTO.builder().id(Long.valueOf(3)).name("Transport").build());
        tagTypes.add(ResTagDTO.builder().id(Long.valueOf(4)).name("Lainnya").build());

        // Karena data selalu ada, langsung return status OK
        return ResponseEntity
                .status(ResponseStatus.OK.getStatus())
                .body(ApiResponse.success(tagTypes, "Success"));
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<ResCategoryDTO>>> getCat(){
        List<ResCategoryDTO> catTypes = new ArrayList<>();

        // Membuat data statis langsung di controller
        catTypes.add(ResCategoryDTO.builder().id(Long.valueOf(1)).name("Pengeluaran").build());
        catTypes.add(ResCategoryDTO.builder().id(Long.valueOf(2)).name("Pemasukan").build());
        catTypes.add(ResCategoryDTO.builder().id(Long.valueOf(3)).name("Pemasukan Split Bill").build());
        catTypes.add(ResCategoryDTO.builder().id(Long.valueOf(4)).name("Pengeluaran Split Bill").build());

        // Karena data selalu ada, langsung return status OK
        return ResponseEntity
                .status(ResponseStatus.OK.getStatus())
                .body(ApiResponse.success(catTypes, "Success"));
    }
}