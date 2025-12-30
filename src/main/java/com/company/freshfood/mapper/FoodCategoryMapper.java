package com.company.freshfood.mapper;

import java.util.List;

import com.company.freshfood.dto.FoodCategoryResponse;
import com.company.freshfood.entity.FoodCategoryEntity;

public class FoodCategoryMapper {

	public static List<FoodCategoryResponse> toFoodCategoryResponseList(List<FoodCategoryEntity> entityList) {
		if (entityList == null || entityList.isEmpty()) {
			return List.of();
		}
		return entityList.stream()
				.map(entity -> FoodCategoryResponse.builder().categoryId(entity.getCategoryId())
						.categoryName(entity.getCategoryName()).parentCategoryId(entity.getParentCategoryId()).build())
				.toList();
	}
}
