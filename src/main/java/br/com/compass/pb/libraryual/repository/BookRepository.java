package br.com.compass.pb.libraryual.repository;

import br.com.compass.pb.libraryual.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
