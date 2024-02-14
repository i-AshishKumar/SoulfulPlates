package com.Group11.soulfulplates.payload.response;

import java.util.Random;

public class OtpResponse {
    private final String OtpCode;

    public OtpResponse() {
        Random random = new Random();
        int otpCode = 1000 + random.nextInt(9000);
        OtpCode = String.valueOf(otpCode);
    }

    public String getOtpCode() {
        return OtpCode;
    }
}
