package br.com.compass.pb.libraryual.dto;

import br.com.compass.pb.libraryual.model.Author;
import lombok.Data;

@Data
public class AuthorDTO {
    private Long id;
    private String name;

    public AuthorDTO(){
    }
    public AuthorDTO(Author entity) {
        id = entity.getId();
        name = entity.getName();
    }
}
