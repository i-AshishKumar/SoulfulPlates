package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.ERole;
import com.Group11.soulfulplates.models.Role;
import com.Group11.soulfulplates.models.Seller;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.request.LoginRequest;
import com.Group11.soulfulplates.payload.request.SignupRequest;
import com.Group11.soulfulplates.payload.response.JwtResponse;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.RoleRepository;
import com.Group11.soulfulplates.repository.SellerRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.security.jwt.JwtUtils;
import com.Group11.soulfulplates.security.services.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;


public class AuthControllerTest {
    @Mock
    UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private SignupRequest signupRequest;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private SecurityContextHolder securityContextHolder;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private LoginRequest loginRequest;

    @InjectMocks
    AuthController auth;

    @BeforeEach
    void setUp(){
        openMocks(this);
    }


    @Test
    void testRegisterUserUsernameTaken (){

        MessageResponse m = new MessageResponse(-1,"Error: Username is already taken!",null);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("tester");
        signupRequest.setEmail("test@tester.com");
        signupRequest.setPassword("testing123");

        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        assertEquals(ResponseEntity.badRequest().body(m),auth.registerUser(signupRequest));

    }

    @Test
    void testRegisterUserEmailTaken(){
        MessageResponse m = new MessageResponse(-1, "Error: Email is already in use!", null);

        when(userRepository.existsByEmail(any())).thenReturn(true);
        assertEquals(ResponseEntity.badRequest().body(m), auth.registerUser(signupRequest));
    }



    @Test
    void testRegisterSuccessful(){
        MessageResponse m = new MessageResponse(1,"User registered successfully!",null);

        Role buyerRole = new Role(ERole.ROLE_BUYER);
//        Role sellerRole = new Role(ERole.ROLE_SELLER);
//        Role adminRole = new Role(ERole.ROLE_ADMIN);
        when(roleRepository.findByName(ERole.ROLE_BUYER)).thenReturn(Optional.of(buyerRole));
        when(userRepository.save(any())).thenReturn(new User());

        assertEquals(ResponseEntity.ok(m),auth.registerUser(signupRequest));
    }


    @Test
    void testRegisterUser_Success() {
        MessageResponse m = new MessageResponse(1,"User registered successfully!",null);

        // Mocking the signUpRequest
        SignupRequest signUpRequest = new SignupRequest();

        // Mocking the userRepository behavior
        when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(false);

        // Mocking the roleRepository behavior
        Role buyerRole = new Role(ERole.ROLE_BUYER);
        when(roleRepository.findByName(ERole.ROLE_BUYER)).thenReturn(java.util.Optional.of(buyerRole));

        // Mocking the passwordEncoder behavior
        when(encoder.encode(signUpRequest.getPassword())).thenReturn("encodedPassword");

        // Test the registerUser method
        ResponseEntity<MessageResponse> responseEntity = auth.registerUser(signUpRequest);

        // Verify userRepository.save is called once
        verify(userRepository, times(1)).save(any());

        // Verify the response
        assertEquals(1, responseEntity.getBody().getCode());
        assertEquals(ResponseEntity.ok(m), auth.registerUser(signUpRequest));
    }

    @Test
    void testAuthenticateUser(){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    }
}
