package com.natalia.spotify.playlistapi.infrastructure.drivenAdapters.adapters.spotify;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;

@Component
public class SpotifyApiClient {

    private static final String GENRES_URL = "https://binaryjazz.us/wp-json/genrenator/v1/genre/10";

    public List<String> getGenres(String token) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List> response =
                restTemplate.getForEntity(GENRES_URL, List.class);

        return response.getBody();
    }
}
