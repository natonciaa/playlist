package com.natalia.spotify.playlistapi.domain.model.playlist;

import com.natalia.spotify.playlistapi.domain.model.song.Song;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    @NonNull
    private String nombre;
    private String descripcion;
    private List<Song> canciones;
}
