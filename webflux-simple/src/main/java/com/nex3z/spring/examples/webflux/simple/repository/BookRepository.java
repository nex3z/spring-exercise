package com.nex3z.spring.examples.webflux.simple.repository;

import com.nex3z.spring.examples.webflux.simple.model.Book;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends R2dbcRepository<Book, Long> {

    @Query("select * from t_book where title=$1")
    Mono<Book> findByTitle(String title);

}
