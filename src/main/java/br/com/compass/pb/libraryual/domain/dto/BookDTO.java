package br.com.compass.pb.libraryual.domain.dto;

import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.domain.entity.Book;
import br.com.compass.pb.libraryual.domain.entity.Genre;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private Integer numPages;
    private Integer year;
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