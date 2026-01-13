package com.company.freshfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.freshfood.entity.FoodImageEntity;

public interface FoodImageRepository extends JpaRepository<FoodImageEntity, Long> {
	List<FoodImageEntity> findByFoodId(Long foodId);
}
