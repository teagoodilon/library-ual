package br.com.compass.pb.libraryual.domain.dto;

import br.com.compass.pb.libraryual.domain.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class GenreDTO {
    private Long id;

    @NotBlank(message = "ORAZIO")
    private String name;

    public GenreDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public GenreDTO(Genre entity) {
        BeanUtils.copyProperties(entity, this);
    }
    public static GenreDTO convertToDto(Genre entity){
        return new GenreDTO(entity.getId(), entity.getName());
    }

    public Genre toEntity(){
        Genre entity = new Genre();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

}
