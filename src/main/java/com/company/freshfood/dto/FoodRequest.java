package com.company.freshfood.dto;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class FoodRequest {
	@Getter
	@Setter
	public static class FoodInsertRequest {
		@NotNull
		private Long categoryId;

		@NotEmpty
		private String foodName;

		@NotEmpty
		private String description;

		@NotNull
		private BigDecimal price;

		private BigDecimal discountPrice;

		@NotNull
		private Integer quantity;

		@NotEmpty
		private String status;

		@NotNull
		private MultipartFile foodImage;
	}

	@Getter
	@Setter
	public static class FoodSearchRequest {

		private List<Long> categoryIds;

		private String foodName;

		private Integer limit;

		private Integer offset;
	}

	@Getter
	@Setter
	public static class FoodDetaiRequest {
		@NotNull
		private Long foodId;
	}

	@Getter
	@Setter
	public static class FoodUpdateRequest {

		@NotNull
		private Long foodId;

		@NotNull
		private Long categoryId;

		@NotEmpty
		private String foodName;

		@NotEmpty
		private String description;

		@NotNull
		private BigDecimal price;

		private BigDecimal discountPrice;

		@NotNull
		private Integer quantity;

		@NotEmpty
		private String status;

		@NotNull
		private MultipartFile foodImage;
	}

	@Getter
	@Setter
	public static class FoodDeleteRequest {
		@NotNull
		private Long foodId;
	}
}
