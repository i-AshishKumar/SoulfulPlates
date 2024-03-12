package com.Group11.soulfulplates.payload.response;

import java.util.Map;

public class CreatePaymentResponse {

    private Integer code;
    private Map<String, Long> data;
    private String description;

    public CreatePaymentResponse(Integer code, Map<String, Long> data, String description) {
        this.code = code;
        this.data = data;
        this.description = description;
    }

    // Getters and Setters
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Long> getData() {
        return data;
    }

    public void setData(Map<String, Long> data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
