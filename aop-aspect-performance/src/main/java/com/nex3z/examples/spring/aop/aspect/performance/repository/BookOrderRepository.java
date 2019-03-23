package com.nex3z.examples.spring.aop.aspect.performance.repository;


import com.nex3z.examples.spring.aop.aspect.performance.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
