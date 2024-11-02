package com.islahdev.ecommersapi.repository;

import com.islahdev.ecommersapi.model.entity.OrderProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Integer> {


}
