package com.natalia.spotify.playlistapi.infrastructure.entryPoints;

import com.natalia.spotify.playlistapi.application.usecase.playlist.CreatePlaylistUseCase;

import com.natalia.spotify.playlistapi.application.usecase.playlist.DeletePlaylistUseCase;
import com.natalia.spotify.playlistapi.application.usecase.playlist.GetAllPlaylistsUseCase;
import com.natalia.spotify.playlistapi.application.usecase.playlist.GetPlaylistByNameUseCase;
import com.natalia.spotify.playlistapi.domain.model.playlist.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {

    private final CreatePlaylistUseCase createUseCase;
    private final GetAllPlaylistsUseCase getAllUseCase;
    private final GetPlaylistByNameUseCase getByNameUseCase;
    private final DeletePlaylistUseCase deleteUseCase;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Playlist playlist) {
        try {
            if (playlist.getNombre() == null || playlist.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre de la playlist es obligatorio");
            }
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
            Optional<Playlist> playlistOpt = getByNameUseCase.getByName(name);

            if (playlistOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Playlist no encontrada: " + name);
            }

            return ResponseEntity.ok(playlistOpt.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al buscar playlist: " + e.getMessage());
        }
    }


    @DeleteMapping("/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        try {
            boolean deleted = deleteUseCase.deleteByName(name);

            if (!deleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Playlist no encontrada: " + name);
            }

            return ResponseEntity.noContent().build(); // 204 No Content

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar playlist: " + e.getMessage());
        }
    }

}
