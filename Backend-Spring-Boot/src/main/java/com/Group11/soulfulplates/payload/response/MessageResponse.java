package com.Group11.SoulfulPlates.payload.response;

import lombok.Data;

@Data
public class MessageResponse {
  private int code;
  private String description;
  private Object data;

  public MessageResponse(int code, String description, Object data) {
    this.code = code;
    this.description = description;
    this.data = data;
  }

}

