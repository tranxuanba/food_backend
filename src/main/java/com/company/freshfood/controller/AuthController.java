package com.company.freshfood.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.freshfood.config.AuthService;
import com.company.freshfood.dto.UserRequest;
import com.company.freshfood.dto.UserResponse;
import com.company.freshfood.dto.UserResponse.CustomUserDetails;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/auth/login")
	public UserResponse.UserLoginResponse login(@RequestBody @Valid UserRequest.LoginRequest request) {
		return authService.login(request);
	}

	@PostMapping("/auth/register")
	public UserResponse.UserCreateResponse registerBuyer(@RequestBody @Valid UserRequest.CreateRequest request) {
		return authService.registerBuyer(request);
	}

	@PostMapping("/users/me")
	public UserResponse.CustomUserDetails userMe(@AuthenticationPrincipal CustomUserDetails userDetails) {
		return userDetails;
	}

}
