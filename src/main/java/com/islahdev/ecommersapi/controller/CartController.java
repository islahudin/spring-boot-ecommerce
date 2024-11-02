package com.islahdev.ecommersapi.controller;

import com.islahdev.ecommersapi.model.request.CartRequest;
import com.islahdev.ecommersapi.model.response.BaseResponse;
import com.islahdev.ecommersapi.model.dto.CartDTO;
import com.islahdev.ecommersapi.service.CartService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/carts")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public BaseResponse getCarts() {
        CartDTO carts = cartService.getCarts();
        return new BaseResponse(true, "Success", carts);
    }

    @PostMapping
    public BaseResponse insertCart(
            @RequestBody CartRequest cartRequest
    ) {
        Boolean result = cartService.insertCart(cartRequest.getProductId(), cartRequest.getQuantity());
        return new BaseResponse(true, "Success", result);

    }

    @DeleteMapping
    public BaseResponse deleteCart(
            @RequestBody CartRequest cartRequest
    ) {
        Boolean result = cartService.deleteCart(cartRequest.getProductId(), cartRequest.getQuantity());
        return new BaseResponse(true, "Success", result);

    }
}
