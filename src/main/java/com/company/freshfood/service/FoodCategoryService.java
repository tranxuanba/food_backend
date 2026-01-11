package com.company.freshfood.service;

import java.util.List;

import com.company.freshfood.dto.FoodCategoryRequest;
import com.company.freshfood.dto.FoodCategoryResponse;
import com.company.freshfood.dto.SearchFoodCategoryResponse;

public interface FoodCategoryService {

	List<FoodCategoryResponse> getAllFoodCategory();

	List<SearchFoodCategoryResponse> getSearchAllFoodCategory(FoodCategoryRequest.FoodCategorySearchRequest request);

	void deleteFoodCategory(FoodCategoryRequest.FoodCategoryDeleteRequest request);
	
	void addFoodCategory(FoodCategoryRequest.FoodCategoryAddRequest request);
	
	void updateFoodCategory(FoodCategoryRequest.FoodCategoryUpdateRequest request);

}
