package com.islahdev.ecommersapi.controller;

import com.islahdev.ecommersapi.model.response.BaseResponse;
import com.islahdev.ecommersapi.model.response.Pong;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/pinge")
@AllArgsConstructor
public class PingController {

    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public BaseResponse ping() {
        BaseResponse response = new BaseResponse();

        try {

            jdbcTemplate.execute("SELECT 1");

            Pong pong = new Pong();
            pong.setMessage("Success");
            pong.setDatabaseConnection(true);

            response.setStatus(true);
            response.setMessage("Database connection success");
            response.setData(pong);

        }catch(Exception e) {
            Pong pong = new Pong();
            pong.setMessage("Failed");
            pong.setErrorMessage(e.getLocalizedMessage());
            pong.setDatabaseConnection(false);

            response.setStatus(false);
            response.setMessage("Database connection failed");
            response.setData(pong);
        }

        return response;
    }

}
