package com.company.freshfood.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.freshfood.dto.CartMeResponse;
import com.company.freshfood.dto.CartRequest;
import com.company.freshfood.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;

	@PostMapping("/cart-me")
	public List<CartMeResponse> getCartMeList(@ModelAttribute CartRequest.CartMeRequest request) {
		List<CartMeResponse> cartList = cartService.getCartMe(request);
		return cartList;
	}

	@PostMapping("/cart-me-update")
	public void cartMeUpdate(@ModelAttribute CartRequest.CartMeUpdateRequest request) {
		cartService.cartMeUpdate(request);
	}

	@PostMapping("/cart-me-delete")
	public void cartMeDelete(@ModelAttribute CartRequest.CartMeDeleteRequest request) {
		cartService.cartMeDelete(request);
	}
}
