package com.natalia.spotify.playlistapi.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "playlists")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistEntity {
    @Id
    private String nombre;

    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongEntity> canciones;
}
