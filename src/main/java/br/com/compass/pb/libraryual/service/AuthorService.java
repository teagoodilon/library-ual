package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.domain.dto.AuthorDTO;
import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.exception.ResourceNotFoundException;
import br.com.compass.pb.libraryual.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private static final String AUTHOR = "Author";
    private static final String AUTHOR_NOT_FOUND = "Author not found with ID: ";

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDTO> findAll() {
        List<Author> authors = authorRepository.findAll();
        if (!authors.isEmpty()){
            return authors.stream()
                    .map(AuthorDTO::convertToDto)
                    .collect(Collectors.toList());
        }
        throw new ResourceNotFoundException(AUTHOR, "There are no records to display");
    }

    public AuthorDTO findById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            return AuthorDTO.convertToDto(author);
        }
        throw new ResourceNotFoundException(AUTHOR, AUTHOR_NOT_FOUND + id);
    }

    public AuthorDTO insert(AuthorDTO authorDTO) {
        Author author = authorDTO.toEntity();
        author.setCreatedAt(LocalDateTime.now());
        Author createdAuthor = authorRepository.saveAndFlush(author);
        return new AuthorDTO(createdAuthor);
    }

    public AuthorDTO update(Long id, AuthorDTO authorDTO) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(optionalAuthor.isPresent()){
            Author author = optionalAuthor.get();
            author.setName(authorDTO.getName());
            author.setUpdatedAt(LocalDateTime.now());
            Author updatedAuthor = authorRepository.saveAndFlush(author);
            return new AuthorDTO(updatedAuthor);
        }
        throw new ResourceNotFoundException(AUTHOR, AUTHOR_NOT_FOUND + id);
    }

    public void delete(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            authorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(AUTHOR, AUTHOR_NOT_FOUND + id);
        }
    }
}