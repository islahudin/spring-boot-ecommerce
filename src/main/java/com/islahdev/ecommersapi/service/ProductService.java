package com.islahdev.ecommersapi.service;

import com.islahdev.ecommersapi.model.ProductSort;
import com.islahdev.ecommersapi.model.entity.ProductEntity;
import com.islahdev.ecommersapi.model.response.PagingInfo;
import com.islahdev.ecommersapi.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public PagingInfo<ProductEntity> getProducts(
            Integer pageNumber,
            Integer pageSize,
            String sort
    ){
        PageRequest pageRequest = PageRequest.of(pageNumber-1,pageSize);

        if (sort != null){
            pageRequest = pageRequest.withSort(mapStringToSort(sort));
        }
        Page<ProductEntity> products = productRepository.findAll(pageRequest);

        return PagingInfo.convertFromPage(products);
    }

    public PagingInfo<ProductEntity> getProducts(
            Integer pageNumber,
            Integer pageSize,
            String q,
            String sort
    ){
        PageRequest pageRequest = PageRequest.of(pageNumber-1,pageSize);

        if (sort != null){
            pageRequest = pageRequest.withSort(mapStringToSort(sort));
        }
        Page<ProductEntity> products = productRepository.filter(q, pageRequest);

        return PagingInfo.convertFromPage(products);
    }

    public PagingInfo<ProductEntity> getProducts(
            Integer pageNumber,
            Integer pageSize,
            String q,
            Integer categoryId,
            String sort
    ){
        PageRequest pageRequest = PageRequest.of(pageNumber-1,pageSize);

        if (sort != null){
            pageRequest = pageRequest.withSort(mapStringToSort(sort));
        }
        Page<ProductEntity> products = productRepository.filter(q, categoryId, pageRequest);

        return PagingInfo.convertFromPage(products);
    }

    public PagingInfo<ProductEntity> getProducts(
            Integer pageNumber,
            Integer pageSize,
            Integer categoryId,
            String sort
    ){
        PageRequest pageRequest = PageRequest.of(pageNumber-1,pageSize);

        if (sort != null){
            pageRequest = pageRequest.withSort(mapStringToSort(sort));
        }
        Page<ProductEntity> products = productRepository.filter(categoryId, pageRequest);

        return PagingInfo.convertFromPage(products);
    }

    private Sort mapStringToSort(String value){
        ProductSort productSort = ProductSort.fromValue(value);
        return switch (productSort){
            case PRICE_ASCENDING -> Sort.by( "price").ascending();
            case PRICE_DESCENDING -> Sort.by( "price").descending();
        };
    }
}
