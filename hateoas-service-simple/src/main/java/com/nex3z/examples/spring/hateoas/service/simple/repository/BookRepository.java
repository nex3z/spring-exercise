package com.nex3z.examples.spring.hateoas.service.simple.repository;

import com.nex3z.examples.spring.hateoas.service.simple.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleInOrderById(List<String> titles);

    Book findByTitle(String title);

}
