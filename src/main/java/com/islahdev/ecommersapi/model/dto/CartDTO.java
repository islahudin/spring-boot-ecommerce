package com.islahdev.ecommersapi.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {

    private Double amount;

    private List<ProductCartDTO> products;


}
