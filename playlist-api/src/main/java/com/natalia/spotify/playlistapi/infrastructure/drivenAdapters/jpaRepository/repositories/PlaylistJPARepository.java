package com.natalia.spotify.playlistapi.infrastructure.drivenAdapters.jpaRepository.repositories;

import com.natalia.spotify.playlistapi.infrastructure.drivenAdapters.jpaRepository.entities.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistJPARepository extends JpaRepository<PlaylistEntity, String> {
    Optional<PlaylistEntity> findByNombre(String nombre);

}
