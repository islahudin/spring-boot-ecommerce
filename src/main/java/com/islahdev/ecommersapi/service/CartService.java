package com.islahdev.ecommersapi.service;

import com.islahdev.ecommersapi.component.JwtUtils;
import com.islahdev.ecommersapi.model.dto.ProductCartDTO;
import com.islahdev.ecommersapi.model.entity.CartEntity;
import com.islahdev.ecommersapi.model.entity.ProductEntity;
import com.islahdev.ecommersapi.model.dto.CartDTO;
import com.islahdev.ecommersapi.repository.CartRepository;
import com.islahdev.ecommersapi.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final JwtUtils jwtUtils;

    public CartDTO getCarts() {

        int userId = jwtUtils.getUserId();

        List<CartEntity> cartEntities = cartRepository.filter(userId);
        CartDTO cartDTO = new CartDTO();

        double amount =0.0;
        ArrayList<ProductCartDTO> productEntities = new ArrayList<>();

        for (CartEntity cartEntity : cartEntities){
            amount += cartEntity.getProduct().price * cartEntity.getQuantity();
            ProductCartDTO productCartDTO = new ProductCartDTO();
            productCartDTO.setId(cartEntity.getProduct().id);
            productCartDTO.setName(cartEntity.getProduct().name);
            productCartDTO.setPrice(roundPrice(cartEntity.getProduct().price));
            productCartDTO.setImage(cartEntity.getProduct().image);
            productCartDTO.setQuantity(cartEntity.getQuantity());
            productEntities.add(productCartDTO);
        }

        cartDTO.setAmount(roundPrice(amount));
        cartDTO.setProducts(productEntities);



        return cartDTO;
    }

    public Boolean insertCart(Integer productId, Integer quantity){

        int userId = jwtUtils.getUserId();

        Optional<ProductEntity> productOptional = productRepository.findById(productId);
        ProductEntity product = productOptional.orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartEntity> cartEntityOptional = cartRepository.getFilter(userId, productId);
        CartEntity cartEntity = cartEntityOptional.orElse(new CartEntity());

        int existingQuantity = cartEntity.getQuantity() == null ? 0 : cartEntity.getQuantity();

        cartEntity.setUserId(userId);
        cartEntity.setProduct(product);
        cartEntity.setQuantity(existingQuantity + quantity);
        cartEntity.setCreatedAt(LocalDateTime.now());
        cartEntity.setUpdatedAt(LocalDateTime.now());

        cartRepository.save(cartEntity);
        return true;

    }

    public Boolean deleteCart(Integer productId, Integer quantity){

        int userId = jwtUtils.getUserId();

        Optional<CartEntity> cartOptional = cartRepository.getFilter(userId, productId);
        CartEntity cartEntity = cartOptional.orElseThrow(() -> new RuntimeException("Product not found"));

        int currentQuantity = cartEntity.getQuantity();
        int finalQuantity = currentQuantity - quantity;

        if (finalQuantity < 0){
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }

        if (finalQuantity == 0){
            // delete
            cartRepository.deleteById(cartEntity.getId());
        }else {
            // save
            cartEntity.setQuantity(finalQuantity);
            cartEntity.setUpdatedAt(LocalDateTime.now());
            cartRepository.save(cartEntity);
        }

        return true;
    }

    private double roundPrice(double price) {
        BigDecimal bigDecimal = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
