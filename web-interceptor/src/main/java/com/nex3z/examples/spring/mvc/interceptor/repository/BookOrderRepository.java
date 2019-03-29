package com.nex3z.examples.spring.mvc.interceptor.repository;

import com.nex3z.examples.spring.mvc.interceptor.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
