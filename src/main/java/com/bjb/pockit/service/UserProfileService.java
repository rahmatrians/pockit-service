package com.bjb.pockit.service;

import com.bjb.pockit.dto.*;
import com.bjb.pockit.entity.UserProfile;
import com.bjb.pockit.repository.UserProfileRepository;
import com.bjb.pockit.util.DateTimeUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;



    @Transactional
    public ApiResponse<ResRegisterDTO> registerUser(ReqRegisterDTO request) {
        String message = "";
        ResRegisterDTO response = new ResRegisterDTO();

        try {
            Optional<UserProfile> isUserExist = userProfileRepository.findByEmail(request.getEmail());

            if (isUserExist.isPresent()) {
                message = "Email already registered, please use other email";
                throw  new Exception(message);
            }

            UserProfile userProfile = UserProfile.builder()
                    .fullname(request.getFullname())
                    .gender(request.getGender())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .phoneNumber(request.getPhoneNumber())
                    .createdAt(DateTimeUtil.generateDateTimeIndonesia())
                    .build();
            userProfile = userProfileRepository.saveAndFlush(userProfile);

            response = ResRegisterDTO.builder()
                    .id(userProfile.getId())
                    .fullname(userProfile.getFullname())
                    .gender(userProfile.getGender())
                    .email(userProfile.getEmail())
                    .phoneNumber(userProfile.getPhoneNumber())
                    .build();

            message = "User registered successfully";

        }catch (Exception e) {
            response = null;
            log.error("Error : {}" + e.getMessage(), e);
        }

        return ApiResponse.<ResRegisterDTO>builder()
                .data(response)
                .message(message)
                .build();
    }


    @Transactional
    public ApiResponse<ResLoginDTO> loginUser(ReqLoginDTO request) {
        String message = "";
        ResLoginDTO response = new ResLoginDTO();

        try {
            Optional<UserProfile> isUserExist = userProfileRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());

            if (!isUserExist.isPresent()) {
                message = "Email or password is wrong, please try again";
                throw  new Exception(message);
            }

            response = ResLoginDTO.builder()
                    .fullname(isUserExist.get().getFullname())
                    .gender(isUserExist.get().getGender())
                    .email(isUserExist.get().getEmail())
                    .phoneNumber(isUserExist.get().getPhoneNumber())
                    .build();

            message = "User logged in successfully";

        }catch (Exception e) {
            response = null;
            log.error("Error : {}" + e.getMessage(), e);
        }

        return ApiResponse.<ResLoginDTO>builder()
                .data(response)
                .message(message)
                .build();
    }

}
