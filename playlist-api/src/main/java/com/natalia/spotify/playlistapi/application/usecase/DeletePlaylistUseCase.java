package com.natalia.spotify.playlistapi.application.usecase;

import com.natalia.spotify.playlistapi.domain.model.playlist.gateways.PlaylistRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePlaylistUseCase {
    private final PlaylistRepository playlistRepository;

    public void deleteByName(String nombre) {
        playlistRepository.deleteByName(nombre);
    }
}
