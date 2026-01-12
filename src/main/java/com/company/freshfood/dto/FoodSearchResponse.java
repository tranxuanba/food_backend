package com.company.freshfood.dto;

import java.math.BigDecimal;

public interface FoodSearchResponse {
	Long getFoodId();
	
	Long getCategoryId();
	
	String getCategoryName();
	
	String getDescription();
	
	Integer getQuantity();
	
	String getStatus();

	String getFoodName();

	BigDecimal getPrice();

	String getImageUrl();
	
	Integer getTotalCount();
}
