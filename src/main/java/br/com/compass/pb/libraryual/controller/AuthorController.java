package br.com.compass.pb.libraryual.controller;

import br.com.compass.pb.libraryual.domain.dto.AuthorDTO;
import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.service.AuthorService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/author")
@Validated
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/")
    public List<AuthorDTO> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
        AuthorDTO authorDTO = authorService.findById(id);
        return ResponseEntity.ok(authorDTO);
    }

    @PostMapping("/")
    public Author insert(@Valid @RequestBody AuthorDTO authorDTO){
        return authorService.insert(authorDTO);
    }

    @PutMapping("/")
    public Author update(@Valid @RequestBody AuthorDTO authorDTO){
        return authorService.update(authorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }

}

