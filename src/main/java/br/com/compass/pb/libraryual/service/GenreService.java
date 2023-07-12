package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.dto.GenreDTO;
import br.com.compass.pb.libraryual.model.Genre;
import br.com.compass.pb.libraryual.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDTO> findAll(){
        List<Genre> genres = genreRepository.findAll();
        return genres.stream()
                .map(GenreDTO::convertToDto)
                .collect(Collectors.toList());
    }

    public GenreDTO findById(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isPresent()) {
            Genre genres = genreOptional.get();
            return GenreDTO.convertToDto(genres);
        }
        return null;
    }

    public Genre insert(GenreDTO genreDTO){
        Genre genre = GenreDTO.convertToEntity(genreDTO);
        genre.setCreatedAt(LocalDateTime.now());
        return genreRepository.saveAndFlush(genre);
    }

    public Genre update (GenreDTO genreDTO){
        Genre genre = GenreDTO.convertToEntity(genreDTO);
        genre.setUpdatedAt(LocalDateTime.now());
        return genreRepository.saveAndFlush(genre);
    }

    public void delete(Long id){
        genreRepository.deleteById(id);
    }
}
