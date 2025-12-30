package com.company.freshfood.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {

	private String code;

	private String message;

	private LocalDateTime timestamp;
}
