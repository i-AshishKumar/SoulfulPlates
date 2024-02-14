package com.Group11.SoulfulPlates.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgetPasswordRequest {
    @NotBlank
    private String email;
}
