package br.com.compass.pb.libraryual.controller;

import br.com.compass.pb.libraryual.dto.AuthorDTO;
import br.com.compass.pb.libraryual.model.Author;
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
        List<AuthorDTO> result = authorService.findAll();
        return  result;
    }

    @PostMapping("/")
    public Author insert(@RequestBody Author object){
        return authorService.insert(object);
    }

    @PutMapping("/")
    public Author update(@RequestBody Author object){
        return authorService.update(object);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }


}
