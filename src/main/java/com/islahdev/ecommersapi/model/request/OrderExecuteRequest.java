package com.islahdev.ecommersapi.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderExecuteRequest {

    @NotBlank(message = "Order id is required")
    private Integer orderId;

    @NotBlank(message = "Password is required")
    private String password;
}
