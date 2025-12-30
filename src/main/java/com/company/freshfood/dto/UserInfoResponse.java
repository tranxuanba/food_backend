package com.company.freshfood.dto;

public interface UserInfoResponse {

	String getEmail();

	String getFullName();

	String getPasswordHash();

	Long getUserId();

	String getPhone();

	String getStatus();

	String getRoleName();
}
