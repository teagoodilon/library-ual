package br.com.compass.pb.libraryual.dto;

import br.com.compass.pb.libraryual.model.Genre;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GenreDTO {
    private Long id;
    private String name;

    public GenreDTO(){}
    public GenreDTO(Long id, @NotBlank String name){
        this.id = id;
        this.name = name;
    }
    public GenreDTO(Genre entity){
        id = entity.getId();
        name = entity.getName();
    }

    public static GenreDTO convertToDto(Genre entity){
        return new GenreDTO(entity.getId(), entity.getName());
    }

    public static Genre convertToEntity(GenreDTO entityDTO){
        Genre genre = new Genre();
        genre.setId(entityDTO.getId());
        genre.setName(entityDTO.getName());
        return genre;
    }

}
