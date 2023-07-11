package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.dto.AuthorDTO;
import br.com.compass.pb.libraryual.model.Author;
import br.com.compass.pb.libraryual.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDTO> findAll(){
        var result = authorRepository.findAll();
        return result.stream().map(x -> new AuthorDTO(x)).toList();
    }

    public Author insert(Author object){
        object.setCreateDate(LocalDateTime.now());
        Author newObject = authorRepository.saveAndFlush(object);
        return newObject;
    }

    public Author update(Author object){
        object.setUpdateDate(LocalDateTime.now());
        return authorRepository.saveAndFlush(object);
    }

    public void delete(Long id){
        Author object = authorRepository.findById(id).get();
        authorRepository.delete(object);
    }

}
