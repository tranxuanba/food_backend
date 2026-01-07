package com.company.freshfood.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class OrderRequest {

	@Getter
	@Setter
	public static class OrderMeRequest {

		@NotEmpty
		private String userId;

	}

	@Getter
	@Setter
	public static class OrderMeCreateRequest {

		@NotEmpty
		private String userId;

		@NotEmpty
		private String receiverName;

		@NotEmpty
		private String addressDetail;

		@NotEmpty
		private String phone;

		@NotEmpty
		private String paymentMethod;

		@NotEmpty
		private String paymentStatus;

		@NotNull
		private BigDecimal totalAmount;

		@NotNull
		private List<CartMeOrderRequest> cartMeOrderList;

	}

	@Getter
	@Setter
	public static class CartMeOrderRequest {

		@NotNull
		private Long foodId;
		
		@NotNull
		private Long cartItemId;

		@NotNull
		private Integer quantity;

		@NotNull
		private BigDecimal price;

	}

}
