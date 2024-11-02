package com.islahdev.ecommersapi.service;

import com.islahdev.ecommersapi.model.entity.CategoryEntity;
import com.islahdev.ecommersapi.model.response.PagingInfo;
import com.islahdev.ecommersapi.repository.CategoryRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public PagingInfo<CategoryEntity> getCategories(
            Integer pageNumber,
            Integer pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        Page<CategoryEntity> categories = categoryRepository.findAll(pageRequest);

        return PagingInfo.convertFromPage(categories);

    }
}
