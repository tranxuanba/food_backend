package com.company.freshfood.dto;

import java.math.BigDecimal;

public interface FoodSearchResponse {
	Long getFoodId();

	String getFoodName();

	BigDecimal getPrice();

	String getImageUrl();
	
	Integer getTotalCount();
}
