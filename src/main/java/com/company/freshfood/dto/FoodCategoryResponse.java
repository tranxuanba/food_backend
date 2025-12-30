package com.company.freshfood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FoodCategoryResponse {

	private Long categoryId;

	private String categoryName;

	private Long parentCategoryId;

}
