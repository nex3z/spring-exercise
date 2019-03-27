package com.nex3z.examples.spring.mvc.exception.handler.repository;

import com.nex3z.examples.spring.mvc.exception.handler.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
