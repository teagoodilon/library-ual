package br.com.compass.pb.libraryual.controller;

import br.com.compass.pb.libraryual.domain.dto.AuthorDTO;
import br.com.compass.pb.libraryual.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return ResponseEntity.ok(authorDTO);
    }

    @PostMapping("/")
    public AuthorDTO insert(@Valid @RequestBody AuthorDTO authorDTO){
        return authorService.insert(authorDTO);
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@PathVariable ("id") Long id, @Valid @RequestBody AuthorDTO authorDTO){
        return authorService.update(id, authorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Long id){
        authorService.delete(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
