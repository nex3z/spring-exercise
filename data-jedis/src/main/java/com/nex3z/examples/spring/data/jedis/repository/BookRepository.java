package com.nex3z.examples.spring.data.jedis.repository;

import com.nex3z.examples.spring.data.jedis.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
