package br.com.compass.pb.libraryual.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "lu_book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer numPages;
    @Column(name = "publication_Year")
    private Integer year;
    private String edition;

    @ManyToOne
    @JoinColumn(name = "idAuthor")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "idGenre")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "idPublishingCompany")
    private PublishingCompany publishingCompany;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private  LocalDateTime updatedAt;
}