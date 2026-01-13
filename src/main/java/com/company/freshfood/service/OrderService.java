package com.company.freshfood.service;

import java.util.List;

import com.company.freshfood.dto.OrderRequest;
import com.company.freshfood.dto.OrderSearchResponse;

public interface OrderService {

	void orderMeCreate(OrderRequest.OrderMeCreateRequest request);

	List<OrderSearchResponse> getAllOrderList(OrderRequest.OrderSearchRequest request);
	
	void orderPaymentStatusUpdate(OrderRequest.OrderUpdatePaymentStatusRequest request);

}
