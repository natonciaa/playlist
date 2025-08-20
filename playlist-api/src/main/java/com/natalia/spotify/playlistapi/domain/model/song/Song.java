package com.natalia.spotify.playlistapi.domain.model.song;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Song {
    private String titulo;
    private String artista;
    private String album;
    private int anno;
    private String genero;
}


