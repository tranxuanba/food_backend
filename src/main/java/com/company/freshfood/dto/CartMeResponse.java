package com.company.freshfood.dto;

import java.math.BigDecimal;

public interface CartMeResponse {
	
	Long getCartItemId();
	
	Long getFoodId();

	String getFoodName();

	String getImageUrl();

	BigDecimal getPrice();

	Integer getQuantity();

	BigDecimal getTotalPrice();
	
	Long getTotalCount();

}
