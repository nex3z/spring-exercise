package com.nex3z.examples.spring.web.ssl.book.service.repository;

import com.nex3z.examples.spring.web.ssl.book.service.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
