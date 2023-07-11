package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.dto.GenreDTO;
import br.com.compass.pb.libraryual.model.Genre;
import br.com.compass.pb.libraryual.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDTO> findAll(){
        var result = genreRepository.findAll();
        return result.stream().map(GenreDTO::new).toList();
    }

    public Genre insert(Genre object){
        object.setCreatedAt(LocalDateTime.now());
        return genreRepository.saveAndFlush(object);
    }

    public Genre update(Genre object){
        object.setUpdateAt(LocalDateTime.now());
        return genreRepository.saveAndFlush(object);
    }

    public void delete(Long id){
        Genre object = genreRepository.findById(id).get();
        genreRepository.delete(object);
    }
}
