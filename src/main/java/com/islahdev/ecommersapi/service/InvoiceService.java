package com.islahdev.ecommersapi.service;

import com.islahdev.ecommersapi.configuration.ApplicationConfig;
import com.islahdev.ecommersapi.model.template.InvoiceRequest;
import com.islahdev.ecommersapi.model.template.InvoiceResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final ApplicationConfig applicationConfig;

    @Value("${xendit.apikey.secret}")
    private String xenditApiKey;

    InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBasicAuth(xenditApiKey,"");
        HttpEntity<InvoiceRequest> request = new HttpEntity<>(invoiceRequest, httpHeaders);

        ResponseEntity<InvoiceResponse> response = applicationConfig.restTemplate()
                .postForEntity(
                        URI.create("https://api.xendit.co/v2/invoices"),
                        request,
                        InvoiceResponse.class
                );

        return response.getBody();
    }

}
