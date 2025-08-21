package com.natalia.spotify.playlistapi.application.usecase.spotify;

import com.natalia.spotify.playlistapi.infrastructure.drivenAdapters.adapters.spotify.SpotifyApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetSpotifyGenresUseCaseTest {

    @Mock
    private SpotifyApiClient spotifyApiClient;

    @InjectMocks
    private GetSpotifyGenresUseCase getSpotifyGenresUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ReturnsGenres() {
        String token = "fake-token";
        List<String> mockGenres = Arrays.asList("rock", "pop", "jazz");

        when(spotifyApiClient.getGenres(token)).thenReturn(mockGenres);

        List<String> result = getSpotifyGenresUseCase.execute(token);

        assertThat(result).isNotEmpty();
        assertThat(result).containsExactly("rock", "pop", "jazz");
        verify(spotifyApiClient, times(1)).getGenres(token);
    }

    @Test
    void testExecute_ReturnsEmptyList() {
        String token = "fake-token";

        when(spotifyApiClient.getGenres(token)).thenReturn(List.of());

        List<String> result = getSpotifyGenresUseCase.execute(token);

        assertThat(result).isEmpty();
        verify(spotifyApiClient, times(1)).getGenres(token);
    }
}

