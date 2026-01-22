package com.company.freshfood.dto;

import java.math.BigDecimal;

public interface FoodDetailResponse {
	Long getFoodId();

	String getFoodName();

	String getStatus();

	BigDecimal getPrice();
	
	BigDecimal getDiscountPrice();

	String getDescription();

	String getCategoryName();

	Integer getQuantity();

	String getImageUrl();
}
