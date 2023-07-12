package br.com.compass.pb.libraryual.repository;

import br.com.compass.pb.libraryual.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
