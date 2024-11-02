package com.islahdev.ecommersapi.controller;

import com.islahdev.ecommersapi.model.entity.UserEntity;
import com.islahdev.ecommersapi.model.response.BaseResponse;
import com.islahdev.ecommersapi.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public BaseResponse getUser() {
        UserEntity userEntity = userService.userEntity();
        return new BaseResponse(true, "Success", userEntity);
    }


}
