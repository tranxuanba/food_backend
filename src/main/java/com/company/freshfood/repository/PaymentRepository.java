package com.company.freshfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.freshfood.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

}
