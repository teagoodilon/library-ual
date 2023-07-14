package br.com.compass.pb.libraryual;

import br.com.compass.pb.libraryual.domain.dto.GenreDTO;
import br.com.compass.pb.libraryual.domain.entity.Genre;
import br.com.compass.pb.libraryual.exception.ResourceNotFoundException;
import br.com.compass.pb.libraryual.repository.GenreRepository;
import br.com.compass.pb.libraryual.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Genre genre = createGenre();
        when(genreRepository.findById(id)).thenReturn(Optional.of(genre));

        GenreDTO result = genreService.findById(id);

        assertEquals(genre.getId(), result.getId());
        assertEquals(genre.getName(), result.getName());

        verify(genreRepository, times(1)).findById(id);
    }

    @Test
    public void testFindById_NotFound() {
        Long invalidId = 80L;
        when(genreRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            genreService.findById(invalidId);
        });

        verify(genreRepository, times(1)).findById(invalidId);
    }

    @Test
    public void testFindAll() {
        List<Genre> genres = new ArrayList<>();
        genres.add(createGenre());
        when(genreRepository.findAll()).thenReturn(genres);

        List<GenreDTO> result = genreService.findAll();

        assertEquals(1, result.size());

        verify(genreRepository, times(1)).findAll();
    }

    @Test
    public void testInsert() {
        GenreDTO genreDTO = createGenreDTO();
        Genre genre = createGenre();
        when(genreRepository.saveAndFlush(any(Genre.class))).thenReturn(genre);

        GenreDTO result = genreService.insert(genreDTO);

        assertEquals(genre.getId(), result.getId());
        assertEquals(genre.getName(), result.getName());

        verify(genreRepository, times(1)).saveAndFlush(any(Genre.class));
    }

    @Test
    public void testUpdate() {
        GenreDTO genreDTO = createGenreDTO();
        Genre genre = createGenre();
        when(genreRepository.findById(genreDTO.getId())).thenReturn(Optional.of(genre));
        when(genreRepository.saveAndFlush(any(Genre.class))).thenReturn(genre);

        GenreDTO result = genreService.update(genreDTO.getId(), genreDTO);

        assertEquals(genre.getId(), result.getId());
        assertEquals(genre.getName(), result.getName());

        verify(genreRepository, times(1)).findById(genreDTO.getId());
        verify(genreRepository, times(1)).saveAndFlush(any(Genre.class));
    }

    @Test
    public void testUpdate_InvalidId() {
        Long invalidId = 80L;
        GenreDTO genreDTO = createGenreDTO();
        when(genreRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            genreService.update(invalidId, genreDTO);
        });

        verify(genreRepository, times(1)).findById(invalidId);
        verify(genreRepository, never()).saveAndFlush(any(Genre.class));
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        Genre genre = createGenre();
        when(genreRepository.findById(id)).thenReturn(Optional.of(genre));

        genreService.delete(id);

        verify(genreRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDelete_InvalidId() {
        Long invalidId = 80L;

        assertThrows(ResourceNotFoundException.class, () -> {
            genreService.delete(invalidId);
        });

        verify(genreRepository, times(0)).deleteById(invalidId);
    }


    private Genre createGenre() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Genre");

        return genre;
    }

    private GenreDTO createGenreDTO() {
        Long id= 1L;
        String name = "Genre";
        GenreDTO genreDTO = new GenreDTO(id, name);

        return genreDTO;
    }
}