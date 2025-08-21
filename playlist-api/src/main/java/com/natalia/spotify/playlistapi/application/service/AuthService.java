package com.natalia.spotify.playlistapi.application.service;

import com.natalia.spotify.playlistapi.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;

    private final Map<String, String> users = new HashMap<>() {{
        put("admin", "admin123");
    }};

    public String login(String username, String password) {
        String storedPassword = users.get(username);

        if (storedPassword != null && storedPassword.equals(password)) {
            return jwtUtil.generateToken(username);
        }

        throw new RuntimeException("Credenciales inválidas");
    }
}
