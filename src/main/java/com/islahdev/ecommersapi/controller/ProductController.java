package com.islahdev.ecommersapi.controller;

import com.islahdev.ecommersapi.service.ProductService;
import com.islahdev.ecommersapi.model.entity.ProductEntity;
import com.islahdev.ecommersapi.model.response.BaseResponse;
import com.islahdev.ecommersapi.model.response.PagingInfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private  final ProductService productService;

    @GetMapping
    public BaseResponse getProducts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String q,
            @RequestParam(required = false, name = "category_id") Integer categoryId,
            @RequestParam(required = false) String sort
    ){

        String key = (q == null ? "NQ" : "Q") + (categoryId == null ? "NC" : "C");

        PagingInfo<ProductEntity> products = switch (key) {
            case "NQC" -> productService.getProducts(page, size, categoryId, sort);
            case "QNC" -> productService.getProducts(page, size, q, sort);
            case "QC" -> productService.getProducts(page, size, q, categoryId, sort);
            default -> productService.getProducts(page, size, sort);
        };

        BaseResponse response = new BaseResponse();
        response.setStatus(true);
        response.setMessage("Success");
        response.setData(products);
        return response;
    }

}
