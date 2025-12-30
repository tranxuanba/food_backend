package com.company.freshfood.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class UserRequest {
	@Getter
	@Setter
	public static class LoginRequest {

		@NotBlank
		private String email;

		@NotBlank
		private String password;
	}

	@Getter
	@Setter
	public static class CreateRequest {

		@NotBlank
		private String fullName;

		@Email
		@NotBlank
		private String email;

		@NotBlank
		private String password;
	}
}
