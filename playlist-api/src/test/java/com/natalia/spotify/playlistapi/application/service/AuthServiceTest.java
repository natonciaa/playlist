package com.natalia.spotify.playlistapi.application.service;
import com.natalia.spotify.playlistapi.infrastructure.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class AuthServiceTest {

    private AuthService authService;
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        authService = new AuthService(jwtUtil);
    }

    @Test
    void shouldReturnTokenWhenCredentialsAreValid() {
        String token = authService.login("admin", "admin123");

        assertThat(token).isNotNull();
        assertThat(jwtUtil.validateToken(token)).isTrue();
    }

    @Test
    void shouldThrowWhenCredentialsInvalid() {
        assertThatThrownBy(() -> authService.login("admin", "wrong"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Credenciales inválidas");
    }
}
