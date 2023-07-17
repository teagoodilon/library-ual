package br.com.compass.pb.libraryual.controller;

import br.com.compass.pb.libraryual.domain.dto.BookDTO;
import br.com.compass.pb.libraryual.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public List<BookDTO> findAll() {
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }
    @PostMapping("/")
    public BookDTO insert(@Valid @RequestBody BookDTO bookDTO) {
        return bookService.insert(bookDTO);
    }

    @PutMapping("/{id}")
    public BookDTO update(@PathVariable("id") Long id,@Valid @RequestBody BookDTO bookDTO) {
        return bookService.update(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
