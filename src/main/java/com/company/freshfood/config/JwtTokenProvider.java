package com.company.freshfood.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	public Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))).build()
				.parseClaimsJws(token).getBody();
	}

	public String getEmail(String token) {
		return getClaims(token).getSubject();
	}

	@SuppressWarnings("unchecked")
	public List<String> getRoles(String token) {
		return getClaims(token).get("roles", List.class);
	}

	public boolean validate(String token) {
		try {
			getClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
