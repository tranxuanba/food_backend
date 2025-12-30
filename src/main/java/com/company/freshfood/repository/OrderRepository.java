package com.company.freshfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.freshfood.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
