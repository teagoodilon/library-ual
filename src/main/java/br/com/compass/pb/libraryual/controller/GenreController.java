package br.com.compass.pb.libraryual.controller;


import br.com.compass.pb.libraryual.domain.dto.GenreDTO;
import br.com.compass.pb.libraryual.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/")
    public List<GenreDTO> findAll(){
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> findById(@PathVariable Long id) {
        GenreDTO genreDTO = genreService.findById(id);
        return ResponseEntity.ok(genreDTO);
    }

    @PostMapping("/")
    public GenreDTO insert(@Valid @RequestBody GenreDTO object){
        return genreService.insert(object);
    }

    @PutMapping("/{id}")
    public GenreDTO update(@PathVariable ("id") Long id, @Valid @RequestBody GenreDTO object){
        return genreService.update(id, object);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        genreService.delete(id);
        return ResponseEntity.ok().build();
    }

}
