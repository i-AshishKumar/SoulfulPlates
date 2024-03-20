package com.Group11.soulfulplates.security;

import com.Group11.soulfulplates.security.jwt.AuthEntryPointJwt;
import com.Group11.soulfulplates.security.jwt.AuthTokenFilter;
import com.Group11.soulfulplates.security.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebSecurityConfigTest {

    @Mock
    UserDetailsServiceImpl userDetailsService;

    @Mock
    AuthEntryPointJwt unauthorizedHandler;

    @Mock
    AuthTokenFilter authTokenFilter;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AuthenticationConfiguration authConfig;

    @InjectMocks
    WebSecurityConfig webSecurityConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticationJwtTokenFilterBean() {
        assertNotNull(webSecurityConfig.authenticationJwtTokenFilter());
    }

    @Test
    void testAuthenticationManagerBean() throws Exception {
        // Mock AuthenticationManager
        AuthenticationManager authenticationManagerMock = mock(AuthenticationManager.class);
        when(authConfig.getAuthenticationManager()).thenReturn(authenticationManagerMock);

        assertNotNull(webSecurityConfig.authenticationManager(authConfig));
    }

    @Test
    void testPasswordEncoderBean() {
        assertNotNull(webSecurityConfig.passwordEncoder());
    }
}
