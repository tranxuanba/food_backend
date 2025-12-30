package com.company.freshfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.freshfood.entity.FoodCategoryEntity;

public interface FoodCategoryRepository extends JpaRepository<FoodCategoryEntity, Long> {
	
	List<FoodCategoryEntity> findByDeletedFlag(String deletedFlag);

}
