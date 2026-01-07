package com.company.freshfood.service;

import com.company.freshfood.dto.OrderRequest;

public interface OrderService {

	void orderMeCreate(OrderRequest.OrderMeCreateRequest request);

}
