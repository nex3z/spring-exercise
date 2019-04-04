package com.nex3z.examples.spring.hateoas.service.simple.repository;

import com.nex3z.examples.spring.hateoas.service.simple.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// collectionResourceRel is used for the _links name in http://localhost:8080/
@RepositoryRestResource(path = "/orders", collectionResourceRel = "orders")
public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
