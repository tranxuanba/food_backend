package com.company.freshfood.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.freshfood.dto.FoodCategoryRequest;
import com.company.freshfood.dto.FoodCategoryResponse;
import com.company.freshfood.dto.SearchFoodCategoryResponse;
import com.company.freshfood.service.FoodCategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodCategoryController {

	private final FoodCategoryService foodCategoryService;

	@PostMapping("/food-categorys")
	public List<FoodCategoryResponse> getFoodCategoryList() {
		return foodCategoryService.getAllFoodCategory();
	}

	@PostMapping("/food-categorys/search")
	public List<SearchFoodCategoryResponse> searchFoodCategoryList(
			@RequestBody FoodCategoryRequest.FoodCategorySearchRequest request) {
		return foodCategoryService.getSearchAllFoodCategory(request);
	}

	@PostMapping("/food-categorys/delete")
	public void deleteFoodCategory(@RequestBody @Valid FoodCategoryRequest.FoodCategoryDeleteRequest request) {
		foodCategoryService.deleteFoodCategory(request);
	}

	@PostMapping("/food-categorys/update")
	public void updateFoodCategory(@RequestBody @Valid FoodCategoryRequest.FoodCategoryUpdateRequest request) {
		foodCategoryService.updateFoodCategory(request);
	}

	@PostMapping("/food-categorys/add")
	public void addFoodCategory(@RequestBody @Valid FoodCategoryRequest.FoodCategoryAddRequest request) {
		foodCategoryService.addFoodCategory(request);
	}
}
