package com.natalia.spotify.playlistapi.application.usecase.playlist;

import com.natalia.spotify.playlistapi.domain.model.playlist.gateways.PlaylistRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePlaylistUseCase {
    private final PlaylistRepository playlistRepository;

    public boolean deleteByName(String nombre) {
        return playlistRepository.deleteByName(nombre);
    }
}
