package com.Group11.soulfulplates.payload.request;

import jakarta.validation.constraints.NotBlank;

public class ForgetPasswordRequest {
    @NotBlank
    private String email;
}
