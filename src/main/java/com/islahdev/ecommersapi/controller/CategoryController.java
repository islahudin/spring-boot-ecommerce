package com.islahdev.ecommersapi.controller;

import com.islahdev.ecommersapi.model.entity.CategoryEntity;
import com.islahdev.ecommersapi.model.response.BaseResponse;
import com.islahdev.ecommersapi.model.response.PagingInfo;
import com.islahdev.ecommersapi.service.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;

    @GetMapping
    public BaseResponse getCategories(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PagingInfo<CategoryEntity> categoriesPage = categoryService.getCategories(page, size);
        return new BaseResponse(true, "Success", categoriesPage);


    }

    }
