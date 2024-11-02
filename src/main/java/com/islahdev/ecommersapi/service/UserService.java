package com.islahdev.ecommersapi.service;

import com.islahdev.ecommersapi.model.entity.UserEntity;
import com.islahdev.ecommersapi.model.request.RegisterRequest;
import com.islahdev.ecommersapi.repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity userEntity(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String phoneNumber = userDetails.getUsername();
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow();

    }

}
