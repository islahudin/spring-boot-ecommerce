package com.islahdev.ecommersapi.repository;

import com.islahdev.ecommersapi.model.entity.CartEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {

//    List<CartEntity> findByUserId(Integer userId);

    @Query("""
            SELECT cart FROM CartEntity cart WHERE
            cart.userId = :userId
            ORDER BY cart.createdAt DESC
            """)
    List<CartEntity> filter(Integer userId);

    @Query("""
            SELECT cart FROM CartEntity cart WHERE
            cart.userId = :userId
            """)
    List<CartEntity> getQuantity(Integer userId);

    @Query("""
            SELECT cart FROM CartEntity cart WHERE
            cart.userId = :userId AND
            cart.product.id = :productId
            """)
    Optional<CartEntity> getFilter(Integer userId, Integer productId);

    @Modifying
    @Transactional
    @Query("""
            DELETE FROM CartEntity cart WHERE
            cart.userId = :userId AND cart.product.id IN :ids
            """)
    void deleteCartByUserIdAndProductIds(Integer userId, List<Integer> ids);
}
