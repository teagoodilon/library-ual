package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.domain.dto.AuthorDTO;
import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDTO> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(AuthorDTO::convertToDto)
                .collect(Collectors.toList());
    }

    public AuthorDTO findById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            return AuthorDTO.convertToDto(author);
        }
        return null;
    }

    public Author insert(AuthorDTO authorDTO) {
        Author author = AuthorDTO.convertToEntity(authorDTO);
        author.setCreatedAt(LocalDateTime.now());
        return authorRepository.saveAndFlush(author);
    }

    public Author update(AuthorDTO authorDTO) {
        Author author = AuthorDTO.convertToEntity(authorDTO);
        author.setUpdatedAt(LocalDateTime.now());
        return authorRepository.saveAndFlush(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

}
