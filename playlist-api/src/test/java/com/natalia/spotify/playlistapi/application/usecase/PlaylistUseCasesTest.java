package com.natalia.spotify.playlistapi.application.usecase;
import com.natalia.spotify.playlistapi.domain.model.playlist.Playlist;
import com.natalia.spotify.playlistapi.application.usecase.playlist.*;
import com.natalia.spotify.playlistapi.domain.model.playlist.gateways.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PlaylistUseCasesTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private CreatePlaylistUseCase createUseCase;

    @InjectMocks
    private GetAllPlaylistsUseCase getAllUseCase;

    @InjectMocks
    private GetPlaylistByNameUseCase getByNameUseCase;

    @InjectMocks
    private DeletePlaylistUseCase deleteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePlaylist() {
        Playlist playlist = new Playlist();
        playlist.setNombre("Lista 1");

        when(playlistRepository.save(playlist)).thenReturn(playlist);

        Playlist result = createUseCase.save(playlist);

        assertThat(result).isNotNull();
        assertThat(result.getNombre()).isEqualTo("Lista 1");
        verify(playlistRepository, times(1)).save(playlist);
    }

    @Test
    void testGetAllPlaylists() {
        Playlist playlist = new Playlist();
        playlist.setNombre("Lista 1");

        when(playlistRepository.findAll()).thenReturn(List.of(playlist));

        List<Playlist> result = getAllUseCase.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Lista 1");
        verify(playlistRepository, times(1)).findAll();
    }

    @Test
    void testGetPlaylistByName_Found() {
        Playlist playlist = new Playlist();
        playlist.setNombre("Lista 1");

        when(playlistRepository.findByName("Lista 1"))
                .thenReturn(Optional.of(playlist));

        Optional<Playlist> result = getByNameUseCase.getByName("Lista 1");

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Lista 1");
        verify(playlistRepository, times(1)).findByName("Lista 1");
    }

    @Test
    void testGetPlaylistByName_NotFound() {
        when(playlistRepository.findByName("Desconocida"))
                .thenReturn(Optional.empty());

        Optional<Playlist> result = getByNameUseCase.getByName("Desconocida");

        assertThat(result).isEmpty();
        verify(playlistRepository, times(1)).findByName("Desconocida");
    }

    @Test
    void testDeletePlaylist() {
      when(playlistRepository.deleteByName("Lista 1")).thenReturn(Boolean.TRUE);

        deleteUseCase.deleteByName("Lista 1");

        verify(playlistRepository, times(1)).deleteByName("Lista 1");
    }
}
