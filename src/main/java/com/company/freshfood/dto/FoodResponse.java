package com.company.freshfood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FoodResponse {

	private Long foodId;

	private Long categoryId;
}
