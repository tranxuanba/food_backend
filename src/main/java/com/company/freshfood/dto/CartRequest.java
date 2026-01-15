package com.company.freshfood.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class CartRequest {

	@Getter
	@Setter
	public static class CartMeRequest {

		private String userId;

	}

	@Getter
	@Setter
	public static class CartMeUpdateRequest {
		
		@NotEmpty
		private String userId;

		@NotNull
		private Long foodId;

		@NotNull
		private Integer quantity;

	}

	@Getter
	@Setter
	public static class CartMeDeleteRequest {

		@NotNull
		private Long cartItemId;

	}

}
