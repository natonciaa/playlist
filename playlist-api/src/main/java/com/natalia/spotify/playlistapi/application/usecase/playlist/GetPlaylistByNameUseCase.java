package com.natalia.spotify.playlistapi.application.usecase.playlist;

import com.natalia.spotify.playlistapi.domain.model.playlist.Playlist;
import com.natalia.spotify.playlistapi.domain.model.playlist.gateways.PlaylistRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetPlaylistByNameUseCase {
    private final PlaylistRepository playlistRepository;

    public Optional<Playlist> getByName(String nombre) {
        return playlistRepository.findByName(nombre);
    }
}

