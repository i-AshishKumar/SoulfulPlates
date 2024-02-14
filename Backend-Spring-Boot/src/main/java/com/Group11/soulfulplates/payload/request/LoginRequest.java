package com.Group11.SoulfulPlates.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
