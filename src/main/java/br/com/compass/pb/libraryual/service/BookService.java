package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.model.Book;
import br.com.compass.pb.libraryual.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book insert(Book object){
        object.setCreatedAt(LocalDateTime.now());
        Book objectNew = bookRepository.saveAndFlush(object);
        return objectNew;
    }

    public Book update(Book object){
        object.setCreatedAt(LocalDateTime.now());
        return bookRepository.saveAndFlush(object);
    }

    public void delete(Long id){
        Book object = bookRepository.findById(id).get();
        bookRepository.delete(object);
    }

}