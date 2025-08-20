package com.natalia.spotify.playlistapi.domain.model.playlist.gateways;

import com.natalia.spotify.playlistapi.domain.model.playlist.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository {
    Playlist save(Playlist playlist);
    List<Playlist> findAll();
    Optional<Playlist> findByName(String nombre);
    void deleteByName(String nombre);
}

