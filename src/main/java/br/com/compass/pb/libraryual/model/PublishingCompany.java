package br.com.compass.pb.libraryual.model;

    import jakarta.persistence.*;
    import lombok.Data;
    import jakarta.validation.constraints.NotBlank;
    import java.time.LocalDateTime;

    @Entity
    @Table(name = "lu_PublishingCompany")
    @Data

    public class PublishingCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateAt;


        public void setCreateDate(LocalDateTime now) {
        }

        public void setUpdateDate(LocalDateTime now) {
        }
    }

