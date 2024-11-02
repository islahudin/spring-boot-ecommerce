package com.islahdev.ecommersapi.service;

import com.islahdev.ecommersapi.model.CustomUserDetails;
import com.islahdev.ecommersapi.model.entity.UserEntity;
import com.islahdev.ecommersapi.repository.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByPhoneNumber(username)
                .map(this::mapFromEntity)
                .orElseThrow(()-> new IllegalArgumentException("User not found"));
    }

    private CustomUserDetails mapFromEntity(UserEntity userEntity) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUsername(userEntity.phoneNumber);
        customUserDetails.setPassword(userEntity.password);
        customUserDetails.setId(userEntity.id);
        customUserDetails.setName(userEntity.name);
        return customUserDetails;
    }

}
