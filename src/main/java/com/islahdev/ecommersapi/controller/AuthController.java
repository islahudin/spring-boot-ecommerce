package com.islahdev.ecommersapi.controller;

import com.islahdev.ecommersapi.model.request.LoginRequest;
import com.islahdev.ecommersapi.model.request.RegisterRequest;
import com.islahdev.ecommersapi.model.response.BaseResponse;
import com.islahdev.ecommersapi.service.AuthService;
import com.islahdev.ecommersapi.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public BaseResponse postRegister(
            @RequestBody RegisterRequest registerRequest
    ) {
        Boolean result = authService.register(registerRequest);
        return new BaseResponse(result, "Success", result);

    }

    @PostMapping("/login")
    public BaseResponse postLogin(
            @RequestBody LoginRequest loginRequest
    ) {
        String token = authService.login(loginRequest);


        return new BaseResponse(true, "Success", token);

    }
}
