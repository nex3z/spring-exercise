package com.nex3z.examples.spring.data.redis.repository.repository;

import com.nex3z.examples.spring.data.redis.repository.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
