package com.company.freshfood.config;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.company.freshfood.dto.UserInfoResponse;
import com.company.freshfood.dto.UserResponse;
import com.company.freshfood.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserResponse.CustomUserDetails loadUserByUsername(String email) {
		List<UserInfoResponse> userInfoList = userRepository.getUserInfo(email);
		if (CollectionUtils.isEmpty(userInfoList)) {
			throw new RuntimeException("Email hoặc mật khẩu không đúng");
		}

		return new UserResponse.CustomUserDetails(userInfoList.get(0));
	}
}
