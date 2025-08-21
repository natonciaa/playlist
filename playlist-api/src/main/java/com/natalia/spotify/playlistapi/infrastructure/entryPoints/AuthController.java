package com.natalia.spotify.playlistapi.infrastructure.entryPoints;

import com.natalia.spotify.playlistapi.application.service.AuthService;
import com.natalia.spotify.playlistapi.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            String username = body.get("username");
            String password = body.get("password");

            String token = authService.login(username, password);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credenciales inválidas");
        }
    }
}
