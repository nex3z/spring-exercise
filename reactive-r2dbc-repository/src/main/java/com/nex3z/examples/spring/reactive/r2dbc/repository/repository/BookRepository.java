package com.nex3z.examples.spring.reactive.r2dbc.repository.repository;

import com.nex3z.examples.spring.reactive.r2dbc.repository.model.Book;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {

    @Query("SELECT * FROM t_book WHERE title = $1")
    Flux<Book> findByTitle(String title);

}
