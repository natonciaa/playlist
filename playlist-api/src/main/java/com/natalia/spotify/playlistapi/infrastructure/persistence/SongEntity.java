package com.natalia.spotify.playlistapi.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "songs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String artista;
    private String album;
    private int anno;
    private String genero;
}
