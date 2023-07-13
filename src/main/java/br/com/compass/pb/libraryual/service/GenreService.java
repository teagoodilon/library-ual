package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.domain.dto.GenreDTO;
import br.com.compass.pb.libraryual.domain.entity.Genre;
import br.com.compass.pb.libraryual.exception.ResourceNotFoundException;
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
        if (!genres.isEmpty()) {
            return genres.stream()
                    .map(GenreDTO::convertToDto)
                    .collect(Collectors.toList());
        }
        throw new ResourceNotFoundException("Genre", "There are no records to display");
    }

    public GenreDTO findById(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isPresent()) {
            Genre genres = genreOptional.get();
            return GenreDTO.convertToDto(genres);
        }
        throw new ResourceNotFoundException("Genre", "Genre not found with ID: " + id);
    }

    public GenreDTO insert(GenreDTO genreDTO){
        Genre genre = genreDTO.toEntity();
        genre.setCreatedAt(LocalDateTime.now());
        Genre createdGenre = genreRepository.saveAndFlush(genre);
        return new GenreDTO(createdGenre);
    }

    public GenreDTO update (Long id, GenreDTO genreDTO){
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if(optionalGenre.isPresent()){
            Genre genre = optionalGenre.get();
            genre.setName(genreDTO.getName());
            genre.setUpdatedAt(LocalDateTime.now());
            Genre updatedGenre = genreRepository.saveAndFlush(genre);
            return new GenreDTO(updatedGenre);
        }
        throw new ResourceNotFoundException("Genre", "Genre not found with ID: " + id);
    }

    public void delete(Long id){
        genreRepository.deleteById(id);
    }
}
