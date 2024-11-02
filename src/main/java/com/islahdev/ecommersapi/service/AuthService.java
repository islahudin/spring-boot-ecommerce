package com.islahdev.ecommersapi.service;

import com.islahdev.ecommersapi.component.JwtUtils;
import com.islahdev.ecommersapi.model.entity.UserEntity;
import com.islahdev.ecommersapi.model.request.LoginRequest;
import com.islahdev.ecommersapi.model.request.RegisterRequest;
import com.islahdev.ecommersapi.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public boolean register(RegisterRequest registerRequest){
        Optional<UserEntity> userEntity = userRepository.findByPhoneNumber(registerRequest.getPhoneNumber());

        if (userEntity.isPresent()){
            // exiting
            return false;
        }else {
            // seve
            try {

                UserEntity newUserEntity = new UserEntity();
                newUserEntity.name = registerRequest.getName();
                newUserEntity.password = bCryptPasswordEncoder.encode(registerRequest.getPassword());
                newUserEntity.phoneNumber = registerRequest.getPhoneNumber();

                userRepository.save(newUserEntity);
                return true;

            }catch (Exception e){
                return false;
            }

        }
    }

    public String login(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getPhoneNumber(),
                loginRequest.getPassword()
        );

        authenticationManager.authenticate(authenticationToken);
        UserEntity userEntity = userRepository.findByPhoneNumber(loginRequest.getPhoneNumber())
                .orElseThrow();

        return jwtUtils.generateToken(userEntity);
    }

}