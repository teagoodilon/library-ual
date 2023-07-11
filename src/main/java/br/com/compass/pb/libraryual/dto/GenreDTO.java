package br.com.compass.pb.libraryual.dto;

import br.com.compass.pb.libraryual.model.Genre;
import lombok.Data;

@Data
public class GenreDTO {
    private Long id;
    private String name;

    public GenreDTO(){}
    public GenreDTO(Genre entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
