package com.company.freshfood.service;

import java.util.List;

import com.company.freshfood.dto.FoodDetailResponse;
import com.company.freshfood.dto.FoodRequest;
import com.company.freshfood.dto.FoodSearchResponse;

public interface FoodService {

	void insertFood(FoodRequest.FoodInsertRequest request);

	List<FoodSearchResponse> getFoodList(FoodRequest.FoodSearchRequest request);

	FoodDetailResponse getFoodDetailInfo(FoodRequest.FoodDetaiRequest request);
	
	void updateFood(FoodRequest.FoodUpdateRequest request);
	
	void deleteFood(FoodRequest.FoodDeleteRequest request);

}
