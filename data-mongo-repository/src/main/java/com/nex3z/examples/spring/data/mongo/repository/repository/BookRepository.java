package com.nex3z.examples.spring.data.mongo.repository.repository;

import com.nex3z.examples.spring.data.mongo.repository.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByTitle(String title);

}
