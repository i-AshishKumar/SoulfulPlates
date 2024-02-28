package com.Group11.soulfulplates.payload.response;

import com.Group11.soulfulplates.models.Seller;
import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private final List<String> roles;
  private String firstname;
  private String contactNumber;
  private boolean notificationFlag;
  private Seller seller; // Add Seller field

  // Constructor without Seller
  public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, String contactNumber, String firstname, boolean notificationFlag) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
    this.firstname = firstname;
    this.notificationFlag = notificationFlag;
    this.contactNumber = contactNumber;
  }

  // Constructor with Seller
  public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, String contactNumber, String firstname, boolean notificationFlag, Seller seller) {
    this(accessToken, id, username, email, roles, contactNumber, firstname, notificationFlag); // Call the constructor without Seller
      this.seller = seller; // Set Seller
  }


}
