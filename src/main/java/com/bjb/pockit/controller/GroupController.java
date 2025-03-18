package com.bjb.pockit.controller;

import com.bjb.pockit.constant.ResponseStatus;
import com.bjb.pockit.dto.ApiResponse;
import com.bjb.pockit.dto.ResPocketTypeDTO;
import com.bjb.pockit.service.CategoryService;
import com.bjb.pockit.service.PocketService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}