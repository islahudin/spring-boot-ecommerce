package com.islahdev.ecommersapi.repository;

import com.islahdev.ecommersapi.model.entity.OrderResultEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderResultRepository extends JpaRepository<OrderResultEntity, Integer> {


}
