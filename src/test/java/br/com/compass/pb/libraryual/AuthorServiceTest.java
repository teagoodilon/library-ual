package br.com.compass.pb.libraryual;

import br.com.compass.pb.libraryual.domain.dto.AuthorDTO;
import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.repository.AuthorRepository;
import br.com.compass.pb.libraryual.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Long authorId = 1L;
        Author author = createAuthor();
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        AuthorDTO result = authorService.findById(authorId);

        assertEquals(author.getId(), result.getId());
        assertEquals(author.getName(), result.getName());

        verify(authorRepository, times(1)).findById(authorId);
    }

    /*@Test
    public void testFindById_NotFound() {
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(nomedaexceçao.class, () -> {
            bookService.findById(bookId);
        });

        verify(bookRepository, times(1)).findById(bookId);
    }*/

    @Test
    public void testFindById_InvalidInput() {
        //modificar caso exista exceção
        Long invalidId = null;

        AuthorDTO result = authorService.findById(invalidId);

        assertNull(result);
        verify(authorRepository, never()).findById(anyLong());
    }


    @Test
    public void testFindAll() {
        List<Author> authors = new ArrayList<>();
        authors.add(createAuthor());
        when(authorRepository.findAll()).thenReturn(authors);

        List<AuthorDTO> result = authorService.findAll();

        assertEquals(1, result.size());

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void testInsert() {
        AuthorDTO authorDTO = createAuthorDTO();
        Author author = createAuthor();
        when(authorRepository.saveAndFlush(any(Author.class))).thenReturn(author);

        AuthorDTO result = authorService.insert(authorDTO);

        assertEquals(author.getId(), result.getId());
        assertEquals(author.getName(), result.getName());

        verify(authorRepository, times(1)).saveAndFlush(any(Author.class));
    }

    @Test
    public void testInsert_NullInput() {
        //nao passa no teste  ainda, precisa da exceção
        AuthorDTO authorDTO = createAuthorDTO();
        authorDTO.setName(null);

        assertThrows(IllegalArgumentException.class, () -> authorService.insert(authorDTO));
        verify(authorRepository, never()).saveAndFlush(any(Author.class));
    }

    @Test
    public void testInsert_InvalidInput() {
        //nao passa no teste  ainda, precisa da exceção
        AuthorDTO authorDTO = createAuthorDTO();
        authorDTO.setName(null);

        assertThrows(IllegalArgumentException.class, () -> {
            authorService.insert(authorDTO);
        });

        Mockito.verifyNoInteractions(authorRepository);
    }



    @Test
    public void testUpdate() {
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

/*    @Test
    public void testUpdate_InvalidId() {
        Long invalidId = 80L;
        BookDTO bookDTO = createBookDTO();
        when(bookRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(nomedamesmaexcessaoqueinsert.class, () -> {
            bookService.update(invalidId, bookDTO);
        });

        verify(bookRepository, times(1)).findById(invalidId);
        verify(bookRepository, never()).saveAndFlush(any(Book.class));
    }*/


    @Test
    public void testDel() {
        //ele passa nesse teste mas nao deveria kaka, pode excluir esse teste depois
        Long bookId = 1L;

        authorService.delete(bookId);

        verify(authorRepository, times(1)).deleteById(bookId);
    }

    @Test
    public void testDelete() {
        //teste delete valido
        Long bookId = 1L;

        Author author = createAuthor();
        when(authorRepository.findById(bookId)).thenReturn(Optional.of(author));

        authorService.delete(bookId);

        verify(authorRepository, times(1)).deleteById(bookId);
    }

    /*@Test
    public void testDelete_InvalidId() {
        Long invalidId = 80L;

        assertThrows(mesmaexceção.class, () -> {
            bookService.delete(invalidId);
        });

        verify(bookRepository, times(1)).deleteById(invalidId);
    }*/


    private Author createAuthor() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Author");

        return author;
    }

    private AuthorDTO createAuthorDTO() {
        Long id= 1L;
        String name = "Author";
        AuthorDTO authorDTO = new AuthorDTO(id, name);

        return authorDTO;
    }
}