package com.nex3z.examples.spring.data.redis.repository.repository;

import com.nex3z.examples.spring.data.redis.repository.model.BookCache;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookCacheRepository extends CrudRepository<BookCache, Long> {

    Optional<BookCache> findOneByTitle(String title);

}
