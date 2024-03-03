package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.ERole;
import com.Group11.soulfulplates.models.Role;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.request.SignupRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.RoleRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


public class AuthControllerTest {
    @Mock
    UserRepository userRepository;


    @Mock
    User user;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private SignupRequest signupRequest;

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
        MessageResponse m = new MessageResponse(-1,"Error: Email is already in use!",null);

        SignupRequest signupRequest = new SignupRequest();

        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        assertEquals(ResponseEntity.badRequest().body(m),auth.registerUser(signupRequest));
        verify(userRepository).existsByEmail(any());
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


}
