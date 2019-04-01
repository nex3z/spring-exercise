package com.nex3z.examples.spring.web.mvc.controller.repository;


import com.nex3z.examples.spring.web.mvc.controller.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
