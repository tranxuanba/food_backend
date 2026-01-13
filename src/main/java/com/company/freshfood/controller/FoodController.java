package com.company.freshfood.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.freshfood.dto.FoodDetailResponse;
import com.company.freshfood.dto.FoodRequest;
import com.company.freshfood.dto.FoodSearchResponse;
import com.company.freshfood.service.FoodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

	private final FoodService foodService;

	@PostMapping("/insert-food")
	public void insertFood(@ModelAttribute FoodRequest.FoodInsertRequest request) {
		foodService.insertFood(request);
	}

	@PostMapping("/food-search")
	public List<FoodSearchResponse> getFoodList(@ModelAttribute FoodRequest.FoodSearchRequest request) {
		List<FoodSearchResponse> foodList = foodService.getFoodList(request);
		return foodList;
	}

	@PostMapping("/food-detail")
	public FoodDetailResponse getFoodList(@ModelAttribute FoodRequest.FoodDetaiRequest request) {
		FoodDetailResponse foodDetail = foodService.getFoodDetailInfo(request);
		return foodDetail;
	}

	@PostMapping("/update-food")
	public void updateFood(@ModelAttribute FoodRequest.FoodUpdateRequest request) {
		foodService.updateFood(request);
	}

	@PostMapping("/delete-food")
	public void deleteFood(@ModelAttribute FoodRequest.FoodDeleteRequest request) {
		foodService.deleteFood(request);
	}
}
