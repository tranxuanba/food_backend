package com.company.freshfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.freshfood.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
	List<PaymentEntity> findByOrderId(Long orderId);
}
