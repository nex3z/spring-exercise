package com.nex3z.examples.spring.data.cache.repository;

import com.nex3z.examples.spring.data.cache.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
