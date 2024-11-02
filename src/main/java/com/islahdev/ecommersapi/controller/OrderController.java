package com.islahdev.ecommersapi.controller;

import com.islahdev.ecommersapi.model.entity.OrderEntity;
import com.islahdev.ecommersapi.model.request.OrderExecuteRequest;
import com.islahdev.ecommersapi.model.response.BaseResponse;
import com.islahdev.ecommersapi.model.response.PagingInfo;
import com.islahdev.ecommersapi.model.template.InvoiceWebhookRequest;
import com.islahdev.ecommersapi.service.OrderService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public BaseResponse getOrders(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PagingInfo<OrderEntity> orders = orderService.getOrders(page, size);
        return new BaseResponse(true, "Success", orders);
    }

    @PostMapping
    public BaseResponse createOrder(
            @RequestParam String ids
    ) {
        OrderEntity orderEntity = orderService.createOrder(ids);
        return new BaseResponse(true, "Success", orderEntity);

    }

    @PostMapping("/execute")
    public BaseResponse executeOrder(
            @RequestBody OrderExecuteRequest request
    ){
        OrderEntity orderEntity = orderService.executeOrder(request.getOrderId(), request.getPassword());
        return new BaseResponse(true, "Success", orderEntity);

    }

    @PostMapping("/webhook")
    public BaseResponse webhook(
            @RequestHeader(name = "x-callback-token") String callbackToken,
            @RequestBody InvoiceWebhookRequest webhookRequest
            ){
        OrderEntity orderEntity = orderService.handleWebhook(webhookRequest, callbackToken);
        return new BaseResponse(true, "Success", orderEntity);

    }
}
