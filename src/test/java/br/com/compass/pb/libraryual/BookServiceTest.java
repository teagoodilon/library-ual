package br.com.compass.pb.libraryual;

import br.com.compass.pb.libraryual.domain.dto.BookDTO;
import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.domain.entity.Book;
import br.com.compass.pb.libraryual.domain.entity.Genre;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import br.com.compass.pb.libraryual.repository.BookRepository;
import br.com.compass.pb.libraryual.service.BookService;
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

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Long bookId = 1L;
        Book book = createBook();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        BookDTO result = bookService.findById(bookId);

        assertEquals(book.getId(), result.getId());
        assertEquals(book.getTitle(), result.getTitle());

        verify(bookRepository, times(1)).findById(bookId);
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

        BookDTO result = bookService.findById(invalidId);

        assertNull(result);
        verify(bookRepository, never()).findById(anyLong());
    }


    @Test
    public void testFindAll() {
        List<Book> books = new ArrayList<>();
        books.add(createBook());
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDTO> result = bookService.findAll();

        assertEquals(1, result.size());

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testInsert() {
        BookDTO bookDTO = createBookDTO();
        Book book = createBook();
        when(bookRepository.saveAndFlush(any(Book.class))).thenReturn(book);

        BookDTO result = bookService.insert(bookDTO);

        assertEquals(book.getId(), result.getId());
        assertEquals(book.getTitle(), result.getTitle());

        verify(bookRepository, times(1)).saveAndFlush(any(Book.class));
    }

    @Test
    public void testInsert_NullInput() {
        //nao passa no teste  ainda, precisa da exceção
        BookDTO bookDTO = createBookDTO();
        bookDTO.setTitle(null);

        assertThrows(IllegalArgumentException.class, () -> bookService.insert(bookDTO));
        verify(bookRepository, never()).saveAndFlush(any(Book.class));
    }

    @Test
    public void testInsert_InvalidInput() {
        //nao passa no teste  ainda, precisa da exceção
        BookDTO bookDTO = createBookDTO();
        bookDTO.setTitle(null);

        assertThrows(IllegalArgumentException.class, () -> {
            bookService.insert(bookDTO);
        });

        Mockito.verifyNoInteractions(bookRepository);
    }



    @Test
    public void testUpdate() {
        BookDTO bookDTO = createBookDTO();
        Book book = createBook();
        when(bookRepository.findById(bookDTO.getId())).thenReturn(Optional.of(book));
        when(bookRepository.saveAndFlush(any(Book.class))).thenReturn(book);

        BookDTO result = bookService.update(bookDTO.getId(), bookDTO);

        assertEquals(book.getId(), result.getId());
        assertEquals(book.getTitle(), result.getTitle());

        verify(bookRepository, times(1)).findById(bookDTO.getId());
        verify(bookRepository, times(1)).saveAndFlush(any(Book.class));
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

        bookService.delete(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    public void testDelete() {
        //teste delete valido
        Long bookId = 1L;

        Book book = createBook();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        bookService.delete(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }

    /*@Test
    public void testDelete_InvalidId() {
        Long invalidId = 80L;

        assertThrows(mesmaexceção.class, () -> {
            bookService.delete(invalidId);
        });

        verify(bookRepository, times(1)).deleteById(invalidId);
    }*/


    private Book createBook() {
        Author author = new Author();
        author.setId(1L);

        Genre genre = new Genre();
        genre.setId(1L);

        PublishingCompany publishingCompany = new PublishingCompany();
        publishingCompany.setId(1L);

        Book book = new Book();
        book.setId(1L);
        book.setTitle("TestBook");
        book.setNumPages(250);
        book.setYear(2003);
        book.setEdition("1");
        book.setPublishingCompany(publishingCompany);
        book.setGenre(genre);
        book.setAuthor(author);
        return book;
    }

    private BookDTO createBookDTO() {
        Author author = new Author();
        author.setId(1L);

        Genre genre = new Genre();
        genre.setId(1L);

        PublishingCompany publishingCompany = new PublishingCompany();
        publishingCompany.setId(1L);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("TestBook");
        bookDTO.setNumPages(250);
        bookDTO.setYear(2003);
        bookDTO.setEdition("1");
        bookDTO.setPublishingCompany(publishingCompany);
        bookDTO.setGenre(genre);
        bookDTO.setAuthor(author);
        return bookDTO;
    }
}
