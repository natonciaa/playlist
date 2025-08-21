package com.natalia.spotify.playlistapi.application.service;

import com.natalia.spotify.playlistapi.domain.model.user.User;
import com.natalia.spotify.playlistapi.infrastructure.drivenAdapters.jpaRepository.repositories.UserRepository;
import com.natalia.spotify.playlistapi.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return jwtUtil.generateToken(username);
        }

        throw new RuntimeException("Credenciales inválidas");
    }
}
