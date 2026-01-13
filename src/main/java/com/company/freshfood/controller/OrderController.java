package com.company.freshfood.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.freshfood.dto.OrderRequest;
import com.company.freshfood.dto.OrderSearchResponse;
import com.company.freshfood.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/order-me-create")
	public void orderMeCreate(@RequestBody OrderRequest.OrderMeCreateRequest request) {
		orderService.orderMeCreate(request);
	}

	@PostMapping("/order-list")
	public List<OrderSearchResponse> getAllOrderList(@RequestBody OrderRequest.OrderSearchRequest request) {
		return orderService.getAllOrderList(request);
	}

	@PostMapping("/order-payment-status-update")
	public void orderPaymentStatusUpdate(@RequestBody OrderRequest.OrderUpdatePaymentStatusRequest request) {
		orderService.orderPaymentStatusUpdate(request);
	}
}
