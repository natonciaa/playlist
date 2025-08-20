package com.natalia.spotify.playlistapi.domain.model.playlist;

import com.natalia.spotify.playlistapi.domain.model.song.Song;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    private String nombre;
    private String descripcion;
    private List<Song> canciones;
}
