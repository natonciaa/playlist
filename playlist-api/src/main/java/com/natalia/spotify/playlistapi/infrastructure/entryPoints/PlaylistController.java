package com.natalia.spotify.playlistapi.infrastructure.entryPoints;

import com.natalia.spotify.playlistapi.application.usecase.CreatePlaylistUseCase;

import com.natalia.spotify.playlistapi.application.usecase.DeletePlaylistUseCase;
import com.natalia.spotify.playlistapi.application.usecase.GetAllPlaylistsUseCase;
import com.natalia.spotify.playlistapi.application.usecase.GetPlaylistByNameUseCase;
import com.natalia.spotify.playlistapi.domain.model.playlist.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final CreatePlaylistUseCase createUseCase;
    private final GetAllPlaylistsUseCase getAllUseCase;
    private final GetPlaylistByNameUseCase getByNameUseCase;
    private final DeletePlaylistUseCase deleteUseCase;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Playlist playlist) {
        try {
            Playlist created = createUseCase.save(playlist);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al crear playlist: " + e.getMessage());
        }    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getAll() {
        return ResponseEntity.ok(getAllUseCase.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        try {
            Optional playlist = getByNameUseCase.getByName(name);

            if (playlist == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Playlist no encontrada: " + name);
            }

            return ResponseEntity.ok(playlist);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al buscar playlist: " + e.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        deleteUseCase.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
}
