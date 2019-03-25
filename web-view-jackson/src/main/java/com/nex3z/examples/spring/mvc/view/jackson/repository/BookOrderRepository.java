package com.nex3z.examples.spring.mvc.view.jackson.repository;


import com.nex3z.examples.spring.mvc.view.jackson.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
