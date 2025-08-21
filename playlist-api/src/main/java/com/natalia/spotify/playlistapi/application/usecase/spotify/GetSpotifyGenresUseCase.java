package com.natalia.spotify.playlistapi.application.usecase.spotify;

import com.natalia.spotify.playlistapi.infrastructure.drivenAdapters.adapters.spotify.SpotifyApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSpotifyGenresUseCase {

    private final SpotifyApiClient spotifyApiClient;

    public List<String> execute(String token) {
        return spotifyApiClient.getGenres(token);
    }
}
