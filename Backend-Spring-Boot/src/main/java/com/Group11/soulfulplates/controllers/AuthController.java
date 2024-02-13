package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.ERole;
import com.Group11.soulfulplates.models.Role;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.request.ForgetPasswordRequest;
import com.Group11.soulfulplates.payload.request.LoginRequest;
import com.Group11.soulfulplates.payload.request.SignupRequest;
import com.Group11.soulfulplates.payload.response.JwtResponse;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.RoleRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.security.jwt.JwtUtils;
import com.Group11.soulfulplates.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_BUYER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "seller":
          Role modRole = roleRepository.findByName(ERole.ROLE_SELLER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_BUYER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

//  @PostMapping("/forget-password")
//  public ResponseEntity<?> generateForgetPasswordCode(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
//    // Generate a random 4-digit code
//    String code = generateRandomCode();
//
//    // Authenticate the user for the purpose of generating a JWT token
//    Authentication authentication = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(forgetPasswordRequest.getUsername(), forgetPasswordRequest.getPassword()));
//
//    // Generate JWT token
//    String jwt = jwtUtils.generateJwtToken(authentication);
//
//    // Here you can send this code to the user's email or phone number
//    // and return the JWT token for further authentication
//
//    return ResponseEntity.ok(new ForgetPasswordResponse(code, jwt));
//  }
@PostMapping("/forget-password")
@PreAuthorize("hasRole('ROLE_BUYER') or hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
public String generateForgetPasswordCode(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
  // Generate a random 4-digit code

//  // Authenticate the user for the purpose of generating a JWT token
//  Authentication authentication = authenticationManager.authenticate(
//          new UsernamePasswordAuthenticationToken(forgetPasswordRequest.getUsername(), forgetPasswordRequest.getPassword()));

  // Generate JWT token
//  String jwt = jwtUtils.generateJwtToken(authentication);

  // Here you can send this code to the user's email or phone number
  // and return the JWT token for further authentication

//  return ResponseEntity.ok(new ForgetPasswordResponse(code, jwt));
  return generateRandomCode();
}

  private String generateRandomCode() {
    Random random = new Random();
    int code = 1000 + random.nextInt(9000); // Random 4-digit code
    return String.valueOf(code);
  }
}
