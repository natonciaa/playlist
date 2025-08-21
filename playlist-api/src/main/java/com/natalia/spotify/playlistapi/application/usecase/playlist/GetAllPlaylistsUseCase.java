package com.natalia.spotify.playlistapi.application.usecase.playlist;

import com.natalia.spotify.playlistapi.domain.model.playlist.Playlist;
import com.natalia.spotify.playlistapi.domain.model.playlist.gateways.PlaylistRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllPlaylistsUseCase {
    private final PlaylistRepository playlistRepository;


    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }
}
