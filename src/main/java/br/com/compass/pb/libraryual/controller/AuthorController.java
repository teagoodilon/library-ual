package br.com.compass.pb.libraryual.controller;

import br.com.compass.pb.libraryual.domain.dto.AuthorDTO;
import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/author")
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
        if (authorDTO != null) {
            return ResponseEntity.ok(authorDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public Author insert(@RequestBody Author object){
        return authorService.insert(AuthorDTO.convertToDto(object));
    }

    @PutMapping("/")
    public Author update(@RequestBody Author object){
        return authorService.update(AuthorDTO.convertToDto(object));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }


}
