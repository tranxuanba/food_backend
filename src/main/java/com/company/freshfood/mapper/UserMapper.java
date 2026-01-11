package com.company.freshfood.mapper;

import com.company.freshfood.dto.UserResponse;
import com.company.freshfood.dto.UserResponse.CustomUserDetails;

public class UserMapper {
	public static UserResponse.UserLoginResponse mapToUserInfoResponse(String token) {
		return UserResponse.UserLoginResponse.builder().token(token).build();
	}

	public static UserResponse.UserDetailsResponse mapToUserDetailsResponse(CustomUserDetails userDetails) {
		return UserResponse.UserDetailsResponse.builder().userId(userDetails.getUserId())
				.username(userDetails.getUsername()).email(userDetails.getEmail())
				.roleName(userDetails.getAuthorities().stream().findFirst().get().toString()).build();
	}
}
