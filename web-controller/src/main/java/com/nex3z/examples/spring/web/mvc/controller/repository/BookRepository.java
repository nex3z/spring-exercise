package com.nex3z.examples.spring.web.mvc.controller.repository;

import com.nex3z.examples.spring.web.mvc.controller.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    List<Book> findByTitleInOrderById(List<String> titles);

}
