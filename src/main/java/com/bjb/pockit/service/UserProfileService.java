package com.bjb.pockit.service;

import com.bjb.pockit.constant.ResponseCode;
import com.bjb.pockit.dto.ApiResponse;
import com.bjb.pockit.dto.ReqRegisterDTO;
import com.bjb.pockit.dto.ResRegisterDTO;
import com.bjb.pockit.dtoWILLREMOVE.GetCreateUserDto;
import com.bjb.pockit.dtoWILLREMOVE.GetUpdateUserDto;
import com.bjb.pockit.dtoWILLREMOVE.ReqCreateUserDto;
import com.bjb.pockit.dtoWILLREMOVE.UpdateUserRequestDto;
import com.bjb.pockit.entity.UserAccountsWILLREMOVE;
import com.bjb.pockit.entity.UserAuthenticationsWILLREMOVE;
import com.bjb.pockit.entity.UserProfile;
import com.bjb.pockit.repository.AccountRepository;
import com.bjb.pockit.repository.UserProfileRepository;
import com.bjb.pockit.repository.UserAuthenticationRepository;
import com.bjb.pockit.util.DateTimeUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;



    @Transactional
    public ApiResponse<ResRegisterDTO> registerUser(ReqRegisterDTO request) {
        String errMessage = "";
        ResRegisterDTO response = new ResRegisterDTO();

        try {
            Optional<UserProfile> isUserExist = userProfileRepository.findByEmail(request.getEmail());

            if (isUserExist.isPresent()) {
                errMessage = "Email already registered, please use other email";
                throw  new Exception(errMessage);
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
                    .fullname(userProfile.getFullname())
                    .gender(userProfile.getGender())
                    .email(userProfile.getEmail())
                    .phoneNumber(userProfile.getPhoneNumber())
                    .build();

            errMessage = "User Successfully registered";

        }catch (Exception e) {
            response = null;
            log.error("Error : {}" + e.getMessage(), e);
        }

        return ApiResponse.<ResRegisterDTO>builder()
                .data(response)
                .message(errMessage)
                .build();
    }

}
