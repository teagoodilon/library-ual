package br.com.compass.pb.libraryual.domain.dto;

import br.com.compass.pb.libraryual.repository.AuthorRepository;
import br.com.compass.pb.libraryual.repository.GenreRepository;
import br.com.compass.pb.libraryual.repository.PublishingCompanyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidAssociatedEntitiesValidator implements ConstraintValidator<ValidAssociatedEntities, BookDTO> {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final PublishingCompanyRepository publishingCompanyRepository;

    public ValidAssociatedEntitiesValidator(AuthorRepository authorRepository,
                                            GenreRepository genreRepository,
                                            PublishingCompanyRepository publishingCompanyRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.publishingCompanyRepository = publishingCompanyRepository;
    }

    @Override
    public void initialize(ValidAssociatedEntities constraintAnnotation) {
    }

    @Override
    public boolean isValid(BookDTO bookDTO, ConstraintValidatorContext context) {
        Long authorId = bookDTO.getAuthor().getId();
        Long genreId = bookDTO.getGenre().getId();
        Long publishingCompanyId = bookDTO.getPublishingCompany().getId();

        boolean isValid = true;

        if (!authorRepository.existsById(authorId)) {
            isValid = false;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid author ID")
                    .addPropertyNode("author")
                    .addConstraintViolation();
        }

        if (!genreRepository.existsById(genreId)) {
            isValid = false;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid genre ID")
                    .addPropertyNode("genre")
                    .addConstraintViolation();
        }

        if (!publishingCompanyRepository.existsById(publishingCompanyId)) {
            isValid = false;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid publishing company ID")
                    .addPropertyNode("publishingCompany")
                    .addConstraintViolation();
        }

        return isValid;
    }
}


