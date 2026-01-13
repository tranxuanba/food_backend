package com.company.freshfood.dto;

import java.math.BigDecimal;

public interface OrderSearchResponse {

	Long getOrderId();

	BigDecimal getTotalAmount();

	String getOrderStatus();

	String getCreatedAt();

	String getPaymentMethod();

	String getPaymentStatus();

	String getTransactionCode();

	String getOrderFoodName();

	String getReceiverName();

	String getPhone();

	String getAddressDetail();

	Integer getTotalCount();
}
