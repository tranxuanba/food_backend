package com.company.freshfood.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.freshfood.dto.OrderRequest;
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
}
