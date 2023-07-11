package br.com.compass.pb.libraryual.controller;

import br.com.compass.pb.libraryual.model.Book;
import br.com.compass.pb.libraryual.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @PostMapping("/")
    public Book insert(@RequestBody Book object){
        return bookService.insert(object);
    }

    @PutMapping("/")
    public Book update(@RequestBody Book object){
        return bookService.update(object);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

}