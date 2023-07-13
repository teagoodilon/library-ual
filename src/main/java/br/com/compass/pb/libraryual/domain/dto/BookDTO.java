package br.com.compass.pb.libraryual.domain.dto;

import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.domain.entity.Book;
import br.com.compass.pb.libraryual.domain.entity.Genre;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
public class BookDTO {

    private Long id;
    @NotBlank
    private String title;
    @Min(1)
    private Integer numPages;
    @Min(1)
    private Integer year;
    @NotBlank
    private String edition;
    private Author author;
    private Genre genre;
    private PublishingCompany publishingCompany;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BookDTO() {
    }

    public BookDTO(Book entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Book toEntity() {
        Book entity = new Book();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}