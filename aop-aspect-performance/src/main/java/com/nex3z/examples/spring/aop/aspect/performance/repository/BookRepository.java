package com.nex3z.examples.spring.aop.aspect.performance.repository;

import com.nex3z.examples.spring.aop.aspect.performance.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
