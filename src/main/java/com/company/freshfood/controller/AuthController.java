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
	public void registerBuyer(@RequestBody @Valid UserRequest.CreateRequest request) {
		authService.registerBuyer(request);
	}

	@PostMapping("/users/me")
	public UserResponse.UserDetailsResponse userMe(@AuthenticationPrincipal CustomUserDetails userDetails) {
		UserResponse.UserDetailsResponse userInfo = authService.userMe(userDetails);
		return userInfo;
	}

}
