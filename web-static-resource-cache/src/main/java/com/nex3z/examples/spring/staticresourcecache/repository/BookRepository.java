package com.nex3z.examples.spring.staticresourcecache.repository;

import com.nex3z.examples.spring.staticresourcecache.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    List<Book> findByTitleInOrderById(List<String> titles);

}
