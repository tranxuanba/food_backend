package com.company.freshfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.freshfood.entity.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

}
