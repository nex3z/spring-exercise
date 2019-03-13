package com.nex3z.examples.spring.data.redis.repository;

import com.nex3z.examples.spring.data.redis.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
