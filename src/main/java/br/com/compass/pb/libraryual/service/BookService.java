package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.domain.dto.BookDTO;
import br.com.compass.pb.libraryual.domain.entity.Book;
import br.com.compass.pb.libraryual.exception.ResourceNotFoundException;
import br.com.compass.pb.libraryual.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        if (!books.isEmpty()) {
            return books.stream()
                    .map(BookDTO::new)
                    .collect(Collectors.toList());
        }
        throw new ResourceNotFoundException("Books", "There are no records to display");
    }

    public BookDTO findById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return new BookDTO(book);
        }
        throw new ResourceNotFoundException("Book", "Book not found with ID: " + id);
    }

    public BookDTO insert(BookDTO bookDTO) {
            Book book = bookDTO.toEntity();
            book.setCreatedAt(LocalDateTime.now());
            Book createdBook = bookRepository.saveAndFlush(book);
            return new BookDTO(createdBook);
    }

    public BookDTO update(Long id, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDTO.getTitle());
            book.setNumPages(bookDTO.getNumPages());
            book.setYear(bookDTO.getYear());
            book.setEdition(bookDTO.getEdition());
            book.setAuthor(bookDTO.getAuthor());
            book.setGenre(bookDTO.getGenre());
            book.setPublishingCompany(bookDTO.getPublishingCompany());
            book.setUpdatedAt(LocalDateTime.now());
            Book updatedBook = bookRepository.saveAndFlush(book);
            return new BookDTO(updatedBook);
        }
        return null;
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}