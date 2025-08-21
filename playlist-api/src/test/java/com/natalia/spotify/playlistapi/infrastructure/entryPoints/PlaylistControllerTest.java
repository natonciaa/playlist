package com.natalia.spotify.playlistapi.infrastructure.entryPoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natalia.spotify.playlistapi.application.usecase.playlist.CreatePlaylistUseCase;
import com.natalia.spotify.playlistapi.application.usecase.playlist.DeletePlaylistUseCase;
import com.natalia.spotify.playlistapi.application.usecase.playlist.GetAllPlaylistsUseCase;
import com.natalia.spotify.playlistapi.application.usecase.playlist.GetPlaylistByNameUseCase;
import com.natalia.spotify.playlistapi.domain.model.playlist.Playlist;
import com.natalia.spotify.playlistapi.infrastructure.security.JwtUtil;
import com.natalia.spotify.playlistapi.infrastructure.security.SecurityTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlaylistController.class)
@Import(SecurityTestConfig.class)
class PlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreatePlaylistUseCase createUseCase;

    @MockBean
    private GetAllPlaylistsUseCase getAllUseCase;

    @MockBean
    private GetPlaylistByNameUseCase getByNameUseCase;

    @MockBean
    private DeletePlaylistUseCase deleteUseCase;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void testCreatePlaylist() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setNombre("Lista 1");
        playlist.setDescripcion("Descripción de prueba");

        when(createUseCase.save(any(Playlist.class))).thenReturn(playlist);

        mockMvc.perform(post("/lists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playlist)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Lista 1"));
    }

    @Test
    void testGetAllPlaylists() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setNombre("Lista 1");

        when(getAllUseCase.findAll()).thenReturn(Collections.singletonList(playlist));

        mockMvc.perform(get("/lists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Lista 1"));
    }

    @Test
    void testGetPlaylistByNameFound() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setNombre("Lista 1");

        when(getByNameUseCase.getByName("Lista 1")).thenReturn(Optional.of(playlist));

        mockMvc.perform(get("/lists/Lista 1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Lista 1"));
    }

    @Test
    void testGetPlaylistByNameNotFound() throws Exception {
        when(getByNameUseCase.getByName("Desconocida")).thenReturn(Optional.empty());

        mockMvc.perform(get("/lists/Desconocida"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Playlist no encontrada: Desconocida"));
    }

    @Test
    void testDeletePlaylist() throws Exception {
        when(deleteUseCase.deleteByName("Lista 1")).thenReturn(Boolean.TRUE);

        mockMvc.perform(delete("/lists/Lista 1"))
                .andExpect(status().isNoContent());
    }
}
