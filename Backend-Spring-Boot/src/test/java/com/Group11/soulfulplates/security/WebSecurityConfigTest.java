package com.Group11.soulfulplates.security;

import com.Group11.soulfulplates.security.jwt.AuthEntryPointJwt;
import com.Group11.soulfulplates.security.jwt.AuthTokenFilter;
import com.Group11.soulfulplates.security.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class WebSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private AuthEntryPointJwt unauthorizedHandler;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private AuthTokenFilter authTokenFilter;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testAuthenticatedEndpointRequiresAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/private/test"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
