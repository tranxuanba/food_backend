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
		
		private String note;

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

	@Getter
	@Setter
	public static class OrderSearchRequest {

		private String transactionCode;

		private String paymentStatus;

		private Integer limit;

		private Integer offset;

	}

	@Getter
	@Setter
	public static class OrderUpdatePaymentStatusRequest {

		@NotNull
		private Long orderId;

		@NotEmpty
		private String paymentStatus;

	}

}
