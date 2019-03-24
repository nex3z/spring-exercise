package com.nex3z.examples.spring.web.controller.simple.repository;

import com.nex3z.examples.spring.web.controller.simple.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
