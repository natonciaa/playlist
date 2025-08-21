package com.natalia.spotify.playlistapi.application.usecase;

import com.natalia.spotify.playlistapi.domain.model.playlist.Playlist;
import com.natalia.spotify.playlistapi.domain.model.playlist.gateways.PlaylistRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePlaylistUseCase {
    private final PlaylistRepository playlistRepository;


    public Playlist save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }
}
