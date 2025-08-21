package com.natalia.spotify.playlistapi.infrastructure.entryPoints;

import com.natalia.spotify.playlistapi.application.usecase.spotify.GetSpotifyGenresUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GetSpotifyGenresUseCase getSpotifyGenresUseCase;

    public GenreController(GetSpotifyGenresUseCase getSpotifyGenresUseCase) {
        this.getSpotifyGenresUseCase = getSpotifyGenresUseCase;
    }

    @GetMapping
    public ResponseEntity<List<String>> getGenres(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.replace("Bearer ", "");
        return ResponseEntity.ok(getSpotifyGenresUseCase.execute(token));
    }
}
