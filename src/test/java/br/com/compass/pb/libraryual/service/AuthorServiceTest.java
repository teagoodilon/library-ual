package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.domain.dto.AuthorDTO;
import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.exception.ResourceNotFoundException;
import br.com.compass.pb.libraryual.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long authorId = 1L;
        Author author = createAuthor();
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        AuthorDTO result = authorService.findById(authorId);

        assertEquals(author.getId(), result.getId());
        assertEquals(author.getName(), result.getName());

        verify(authorRepository, times(1)).findById(authorId);
    }
    @Test
    void testFindById_InvalidInput() {
        assertThrows(ResourceNotFoundException.class, () -> authorService.findById(null));
        verify(authorRepository, never()).findById(anyLong());
    }

    @Test
    void testFindById_NotFound() {
        Long invalidId = 80L;
        when(authorRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authorService.findById(invalidId));

        verify(authorRepository, times(1)).findById(invalidId);
    }

    @Test
    void testFindAll() {
        List<Author> authors = new ArrayList<>();
        authors.add(createAuthor());
        when(authorRepository.findAll()).thenReturn(authors);

        List<AuthorDTO> result = authorService.findAll();

        assertEquals(1, result.size());

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testFindAllWhenEmpty() {
        when(authorRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> authorService.findAll());

        verify(authorRepository, times(1)).findAll();
    }


    @Test
    void testInsert() {
        AuthorDTO authorDTO = createAuthorDTO();
        Author author = createAuthor();
        when(authorRepository.saveAndFlush(any(Author.class))).thenReturn(author);

        AuthorDTO result = authorService.insert(authorDTO);

        assertEquals(author.getId(), result.getId());
        assertEquals(author.getName(), result.getName());

        verify(authorRepository, times(1)).saveAndFlush(any(Author.class));
    }

    @Test
    void testUpdate() {
        AuthorDTO authorDTO = createAuthorDTO();
        Author author = createAuthor();
        when(authorRepository.findById(authorDTO.getId())).thenReturn(Optional.of(author));
        when(authorRepository.saveAndFlush(any(Author.class))).thenReturn(author);

        AuthorDTO result = authorService.update(authorDTO.getId(), authorDTO);

        assertEquals(author.getId(), result.getId());
        assertEquals(author.getName(), result.getName());

        verify(authorRepository, times(1)).findById(authorDTO.getId());
        verify(authorRepository, times(1)).saveAndFlush(any(Author.class));
    }

    @Test
    void testUpdate_InvalidId() {
        Long invalidId = 80L;
        AuthorDTO authorDTO = createAuthorDTO();
        when(authorRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authorService.update(invalidId, authorDTO));

        verify(authorRepository, times(1)).findById(invalidId);
        verify(authorRepository, never()).saveAndFlush(any(Author.class));
    }

    @Test
    void testDelete() {
        Long id = 1L;

        Author author = createAuthor();
        when(authorRepository.findById(id)).thenReturn(Optional.of(author));

        authorService.delete(id);

        verify(authorRepository, times(1)).deleteById(id);
    }

    @Test
    void testDelete_InvalidId() {
        Long invalidId = 80L;

        assertThrows(ResourceNotFoundException.class, () -> authorService.delete(invalidId));

        verify(authorRepository, times(0)).deleteById(invalidId);
    }


    private Author createAuthor() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Author");
        return author;
    }

    private AuthorDTO createAuthorDTO() {
        Long id= 1L;
        String name = "Author";
        return new AuthorDTO(id, name);
    }
}