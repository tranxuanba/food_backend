package com.company.freshfood.mapper;

import com.company.freshfood.dto.FoodRequest;
import com.company.freshfood.dto.FoodResponse;

public class FoodMapper {
	public static FoodResponse toResponse(FoodRequest.FoodInsertRequest request) {
		return FoodResponse.builder().foodId(request.getCategoryId()).categoryId(request.getCategoryId()).build();
	}
}
