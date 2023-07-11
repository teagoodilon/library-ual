package br.com.compass.pb.libraryual.dto;

import br.com.compass.pb.libraryual.model.Author;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorDTO {
    private Long id;
    private String name;

    public AuthorDTO(){
    }
    public AuthorDTO(Long id, @NotBlank String name) {
        this.id = id;
        this.name = name;
    }
    public AuthorDTO(Author entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public static AuthorDTO convertToDto(Author author) {
        return new AuthorDTO(author.getId(), author.getName());
    }

    public static Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        return author;
    }

}
