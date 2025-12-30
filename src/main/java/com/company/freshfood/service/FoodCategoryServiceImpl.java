package com.company.freshfood.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.freshfood.dto.FoodCategoryResponse;
import com.company.freshfood.entity.FoodCategoryEntity;
import com.company.freshfood.mapper.FoodCategoryMapper;
import com.company.freshfood.repository.FoodCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {

	private final FoodCategoryRepository foodCategoryRepository;

	@Override
	@Transactional(readOnly = true)
	public List<FoodCategoryResponse> getAllFoodCategory() {
		List<FoodCategoryEntity> entities = foodCategoryRepository.findByDeletedFlag("0");

		return FoodCategoryMapper.toFoodCategoryResponseList(entities);
	}

}
