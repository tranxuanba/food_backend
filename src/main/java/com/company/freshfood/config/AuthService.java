package com.company.freshfood.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.company.freshfood.dto.UserInfoResponse;
import com.company.freshfood.dto.UserRequest;
import com.company.freshfood.dto.UserResponse;
import com.company.freshfood.dto.UserResponse.CustomUserDetails;
import com.company.freshfood.entity.CartEntity;
import com.company.freshfood.entity.UserEntity;
import com.company.freshfood.mapper.UserMapper;
import com.company.freshfood.repository.CartRepository;
import com.company.freshfood.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public UserResponse.UserLoginResponse login(UserRequest.LoginRequest request) {

		List<UserInfoResponse> userInfoList = userRepository.getUserInfo(request.getEmail());
		if (CollectionUtils.isEmpty(userInfoList)) {
			throw new RuntimeException("Email hoặc mật khẩu không đúng");
		}

		if (!passwordEncoder.matches(request.getPassword(), userInfoList.get(0).getPasswordHash())) {
			throw new RuntimeException("Email hoặc mật khẩu không đúng");
		}
		String token = jwtService.generateToken(userInfoList.get(0));
		return UserMapper.mapToUserInfoResponse(token);
	}

	public void registerBuyer(UserRequest.CreateRequest request) {

		if (userRepository.existsByEmail(request.getEmail(), "0")) {
			throw new RuntimeException("Email đã được sử dụng");
		}

		UserEntity user = new UserEntity();
		user.setFullName(request.getFullName());
		user.setEmail(request.getEmail());
		user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
		user.setRoleId(2L);
		user.setStatus("0");
		user = userRepository.save(user);

		CartEntity cart = new CartEntity();
		cart.setUpdatedAt(LocalDateTime.now());
		cart.setCreateUserId(user.getUserId().toString());
		cart.setCreateUserName(request.getFullName());
		cartRepository.save(cart);
	}

	public UserResponse.UserDetailsResponse userMe(CustomUserDetails userDetails) {
		return UserMapper.mapToUserDetailsResponse(userDetails);
	}
}
