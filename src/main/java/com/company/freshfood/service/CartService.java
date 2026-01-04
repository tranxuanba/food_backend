package com.company.freshfood.service;

import java.util.List;

import com.company.freshfood.dto.CartMeResponse;
import com.company.freshfood.dto.CartRequest;

public interface CartService {

	void cartMeUpdate(CartRequest.CartMeUpdateRequest request);

	void cartMeDelete(CartRequest.CartMeDeleteRequest request);

	List<CartMeResponse> getCartMe(CartRequest.CartMeRequest request);

}
