package com.islahdev.ecommersapi.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductCartDTO {

    private Integer id;
    private String name;
    private Double price;
    private String image;
    private Integer quantity;
}
