package com.company.freshfood.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.freshfood.dto.FoodCategoryResponse;
import com.company.freshfood.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodCategoryController {

	private final FoodCategoryService foodCategoryService;

	@PostMapping("/food-categorys")
	public List<FoodCategoryResponse> getActive() {
		return foodCategoryService.getAllFoodCategory();
	}

}
