package com.company.freshfood.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class FoodCategoryRequest {
	@Getter
	@Setter
	public static class FoodCategorySearchRequest {

		private String categoryName;
	}

	@Getter
	@Setter
	public static class FoodCategoryDeleteRequest {

		@NotNull
		private Long categoryId;
	}

	@Getter
	@Setter
	public static class FoodCategoryAddRequest {

		private Long parentCategoryId;

		@NotEmpty
		private String categoryName;
	}
	
	@Getter
	@Setter
	public static class FoodCategoryUpdateRequest {

		@NotNull
		private Long categoryId;
		
		@NotEmpty
		private String categoryName;
		
		private Long parentCategoryId;
	}
}
