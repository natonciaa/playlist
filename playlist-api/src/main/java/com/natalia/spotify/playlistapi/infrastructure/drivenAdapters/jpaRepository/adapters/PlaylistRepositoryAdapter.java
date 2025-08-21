package com.natalia.spotify.playlistapi.infrastructure.drivenAdapters.jpaRepository.adapters;


import com.natalia.spotify.playlistapi.domain.model.playlist.Playlist;
import org.reactivecommons.utils.ObjectMapper;
import com.natalia.spotify.playlistapi.domain.model.playlist.gateways.PlaylistRepository;
import com.natalia.spotify.playlistapi.infrastructure.drivenAdapters.jpaRepository.entities.PlaylistEntity;
import com.natalia.spotify.playlistapi.infrastructure.drivenAdapters.jpaRepository.repositories.PlaylistJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaylistRepositoryAdapter implements PlaylistRepository {

    private final PlaylistJPARepository jpaRepo;
    private final ObjectMapper mapper;

    @Override
    public Playlist save(Playlist playlist) {
        PlaylistEntity entity = mapper.map(playlist, PlaylistEntity.class);
         return mapper.map(this.jpaRepo.save(entity), Playlist.class);
    }

    @Override
    public List<Playlist> findAll() {
        return jpaRepo.findAll().stream().map(p -> mapper.map(p, Playlist.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<Playlist> findByName(String nombre) {
        return jpaRepo.findById(nombre).map(p -> mapper.map(p, Playlist.class));
    }

    @Override
    public void deleteByName(String nombre) {
        jpaRepo.deleteById(nombre);
    }

}
