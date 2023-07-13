package br.com.compass.pb.libraryual.domain.dto;

import br.com.compass.pb.libraryual.domain.entity.Author;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AuthorDTO {
    private Long id;
    @NotBlank
    private String name;

    public AuthorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorDTO(Author entity) {
        BeanUtils.copyProperties(entity, this);
    }
    public static AuthorDTO convertToDto(Author author) {
        return new AuthorDTO(author.getId(), author.getName());
    }

    public Author toEntity() {
        Author entity = new Author();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

}
