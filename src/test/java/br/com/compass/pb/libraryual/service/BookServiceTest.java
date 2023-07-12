package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.domain.entity.Book;
import br.com.compass.pb.libraryual.domain.entity.Genre;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import br.com.compass.pb.libraryual.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insert() {
        PublishingCompany publishingCompany = new PublishingCompany();
        publishingCompany.setName("Arqueiro");


        Genre genre = new Genre();
        genre.setName("Distopic");

        Author author = new Author();
        author.setName("Aldous Huxley");

        Book book = new Book();
        book.setId(null);
        book.setTitle("Brave new World");
        book.setNumPages(250);
        book.setYear(1986);
        book.setEdition("2");
        book.setPublishingCompany(publishingCompany);
        book.setGenre(genre);
        book.setAuthor(author);

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setTitle("Brave new World");
        savedBook.setNumPages(250);
        savedBook.setYear(1986);
        savedBook.setEdition("2");
        savedBook.setPublishingCompany(publishingCompany);
        savedBook.setGenre(genre);
        savedBook.setAuthor(author);

        Mockito.when(bookRepository.saveAndFlush(book)).thenReturn(savedBook);

        Book result = bookService.insert(book);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Brave new World", result.getTitle());
        assertEquals(250, result.getNumPages());
        assertEquals(1986, result.getYear());
        assertEquals("2", result.getEdition());
        assertEquals(publishingCompany, result.getPublishingCompany());
        assertEquals(genre, result.getGenre());
        assertEquals(author, result.getAuthor());

    }

}