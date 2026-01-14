package com.company.freshfood.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.company.freshfood.dto.OrderRequest;
import com.company.freshfood.dto.OrderRequest.CartMeOrderRequest;
import com.company.freshfood.dto.OrderRequest.OrderSearchRequest;
import com.company.freshfood.dto.OrderRequest.OrderUpdatePaymentStatusRequest;
import com.company.freshfood.dto.OrderSearchResponse;
import com.company.freshfood.entity.AddressEntity;
import com.company.freshfood.entity.OrderEntity;
import com.company.freshfood.entity.OrderItemEntity;
import com.company.freshfood.entity.PaymentEntity;
import com.company.freshfood.exception.ResourceNotFoundException;
import com.company.freshfood.repository.AddressRepository;
import com.company.freshfood.repository.CartItemRepository;
import com.company.freshfood.repository.OrderItemRepository;
import com.company.freshfood.repository.OrderRepository;
import com.company.freshfood.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final OrderItemRepository orderItemRepository;

	private final AddressRepository addressRepository;

	private final PaymentRepository paymentRepository;

	private final CartItemRepository cartItemRepository;

	@Override
	public void orderMeCreate(OrderRequest.OrderMeCreateRequest request) {
		// đăng ký địa chỉ
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setReceiverName(request.getReceiverName());
		addressEntity.setAddressDetail(request.getAddressDetail());
		addressEntity.setPhone(request.getPhone());
		addressEntity = addressRepository.save(addressEntity);
		// đăng ký order
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setAddressId(addressEntity.getAddressId());
		orderEntity.setTotalAmount(request.getTotalAmount());
		orderEntity.setCreateUserId(request.getUserId());
		orderEntity.setOrderStatus("0");
		orderEntity.setCreatedAt(LocalDateTime.now());
		orderEntity.setNote(request.getNote());
		orderEntity = orderRepository.save(orderEntity);
		// đăng ký payment
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setOrderId(orderEntity.getOrderId());
		paymentEntity.setPaymentMethod(request.getPaymentMethod()); // 0-trả tiền mặt khi nhận hàng
		paymentEntity.setPaymentStatus(request.getPaymentStatus()); // 0-chưa trả, 1-đã thanh toán
		paymentEntity.setTransactionCode("BADUNG-" + orderEntity.getOrderId());
		paymentRepository.save(paymentEntity);
		// đang ký order item
		List<Long> cartItemIds = new ArrayList<>();
		for (CartMeOrderRequest orderRequest : request.getCartMeOrderList()) {
			OrderItemEntity orderItemEntity = new OrderItemEntity();
			orderItemEntity.setOrderId(orderEntity.getOrderId());
			orderItemEntity.setFoodId(orderRequest.getFoodId());
			orderItemEntity.setQuantity(orderRequest.getQuantity());
			orderItemEntity.setPrice(orderRequest.getPrice());
			orderItemRepository.save(orderItemEntity);
			cartItemIds.add(orderRequest.getCartItemId());
		}
		// xóa cart item theo user
		cartItemRepository.deleteAllById(cartItemIds);
	}

	@Override
	public List<OrderSearchResponse> getAllOrderList(OrderSearchRequest request) {
		return orderRepository.getOrderedBySearchAll(request.getTransactionCode(), request.getPaymentStatus(),
				request.getLimit(), request.getOffset());
	}

	@Override
	public void orderPaymentStatusUpdate(OrderUpdatePaymentStatusRequest request) {
		List<PaymentEntity> paymentList = paymentRepository.findByOrderId(request.getOrderId());
		if (CollectionUtils.isEmpty(paymentList)) {
			throw new ResourceNotFoundException("Không có sản phẩm tương ứng");
		}
		paymentList.get(0).setPaymentStatus(request.getPaymentStatus());
		paymentRepository.save(paymentList.get(0));
	}
}
