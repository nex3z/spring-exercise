package com.nex3z.examples.spring.staticresourcecache.repository;

import com.nex3z.examples.spring.staticresourcecache.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
