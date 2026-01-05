package com.company.freshfood.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

	    @NotBlank(message = "Họ tên không được để trống")
	    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
	    private String fullName;

	    @NotBlank(message = "Email không được để trống")
	    @Email(message = "Email không đúng định dạng")
	    private String email;

	    @NotBlank(message = "Mật khẩu không được để trống")
	    @Size(min = 6, max = 12, message = "Mật khẩu phải từ 6 đến 12 ký tự")
	    private String password;
	}

}
