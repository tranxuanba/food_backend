package com.company.freshfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.freshfood.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

}
