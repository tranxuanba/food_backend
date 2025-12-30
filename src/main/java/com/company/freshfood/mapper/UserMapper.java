package com.company.freshfood.mapper;

import com.company.freshfood.dto.UserInfoResponse;
import com.company.freshfood.dto.UserResponse;
import com.company.freshfood.entity.UserEntity;

public class UserMapper {
	public static UserResponse.UserLoginResponse mapToUserInfoResponse(UserInfoResponse user, String token) {
		return UserResponse.UserLoginResponse.builder().email(user.getEmail()).fullName(user.getFullName())
				.passwordHash(user.getPasswordHash()).userId(user.getUserId()).phone(user.getPhone())
				.status(user.getStatus()).roleName(user.getRoleName()).token(token).build();
	}

	public static UserResponse.UserCreateResponse mapToUserCreateResponse(UserEntity user) {
		return UserResponse.UserCreateResponse.builder().userId(user.getUserId()).fullName(user.getFullName())
				.email(user.getEmail()).build();
	}
}
