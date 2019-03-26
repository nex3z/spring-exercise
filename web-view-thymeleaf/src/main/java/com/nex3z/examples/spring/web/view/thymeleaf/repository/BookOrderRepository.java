package com.nex3z.examples.spring.web.view.thymeleaf.repository;

import com.nex3z.examples.spring.web.view.thymeleaf.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
