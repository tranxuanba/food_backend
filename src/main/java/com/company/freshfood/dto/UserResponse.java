package com.company.freshfood.dto;

import java.util.Collection;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserResponse {

	@Getter
	@Setter
	@Builder
	public static class UserLoginResponse {

		private String email;

		private String fullName;

		private String passwordHash;

		private Long userId;

		private String phone;

		private String status;

		private String roleName;

		private String token;
	}

	@Getter
	@Setter
	@Builder
	public static class UserCreateResponse {

		private Long userId;

		private String fullName;

		private String email;

		private String roleName;

	}

	@SuppressWarnings("serial")
	public static class CustomUserDetails implements UserDetails {

		private final Long userId;
		private final String username; // email hoặc username
		private final String password;
		private final Collection<? extends GrantedAuthority> authorities;

		public CustomUserDetails(UserInfoResponse user) {
			this.userId = user.getUserId();
			this.username = user.getFullName();
			this.password = user.getPasswordHash();
			this.authorities = Stream.of(user.getRoleName()).map(SimpleGrantedAuthority::new).toList();
		}

		public Long getUserId() {
			return userId;
		}

		@Override
		public String getUsername() {
			return username;
		}

		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return authorities;
		}

		// các method còn lại trả true
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	}

}
