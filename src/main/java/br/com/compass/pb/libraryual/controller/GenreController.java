package br.com.compass.pb.libraryual.controller;


import br.com.compass.pb.libraryual.dto.GenreDTO;
import br.com.compass.pb.libraryual.model.Genre;
import br.com.compass.pb.libraryual.service.GenreService;
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

    @PostMapping("/")
    public Genre insert(@RequestBody Genre object){
        return genreService.insert(object);
    }

    @PutMapping("/")
    public Genre update(@RequestBody Genre object){
        return genreService.update(object);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        genreService.delete(id);
        return ResponseEntity.ok().build();
    }

}
