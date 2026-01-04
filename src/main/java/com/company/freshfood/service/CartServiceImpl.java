package com.company.freshfood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.company.freshfood.dto.CartMeResponse;
import com.company.freshfood.dto.CartMeUpdateResponse;
import com.company.freshfood.dto.CartRequest;
import com.company.freshfood.entity.CartEntity;
import com.company.freshfood.entity.CartItemEntity;
import com.company.freshfood.exception.ResourceNotFoundException;
import com.company.freshfood.repository.CartItemRepository;
import com.company.freshfood.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;

	private final CartItemRepository cartItemRepository;

	@Override
	public void cartMeUpdate(CartRequest.CartMeUpdateRequest request) {
		// tìm xem với món ăn hiện tại có trong cart hay chưa
		List<CartMeUpdateResponse> cartMeUpdateList = new ArrayList<>();
		cartMeUpdateList = cartRepository.findCartMeIsExiest(request.getUserId(), request.getFoodId());
		CartItemEntity cartItemEntity;
		if (CollectionUtils.isEmpty(cartMeUpdateList)) {
			List<CartEntity> cartEntityList = new ArrayList<>();
			cartEntityList = cartRepository.findByCreateUserIdAndDeletedFlag(request.getUserId(), "0");
			if (CollectionUtils.isEmpty(cartEntityList)) {
				throw new ResourceNotFoundException("Giỏ hàng không tồn tại");
			}
			cartItemEntity = new CartItemEntity();
			cartItemEntity.setCartId(cartEntityList.get(0).getCartId());
			cartItemEntity.setFoodId(request.getFoodId());
			cartItemEntity.setQuantity(request.getQuantity());
		} else {
			cartItemEntity = cartItemRepository.findById(cartMeUpdateList.get(0).getCartItemId())
					.orElseThrow(() -> new ResourceNotFoundException("Cart item không tồn tại"));
			cartItemEntity.setQuantity(request.getQuantity());
		}
		cartItemRepository.save(cartItemEntity);
	}

	@Override
	public void cartMeDelete(CartRequest.CartMeDeleteRequest request) {
		CartItemEntity cartItemEntity = cartItemRepository.findById(request.getCartItemId())
				.orElseThrow(() -> new ResourceNotFoundException("Cart item không tồn tại"));
		cartItemRepository.delete(cartItemEntity);
	}

	@Override
	public List<CartMeResponse> getCartMe(CartRequest.CartMeRequest request) {
		List<CartMeResponse> cartList = new ArrayList<>();
		cartList = cartRepository.findCartListByUserId(request.getUserId());
		return cartList;
	}
}
