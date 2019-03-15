package com.nex3z.examples.spring.data.redis.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "data-redis-repository-books", timeToLive = 60)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class BookCache {

    @Id
    private Long id;

    @Indexed
    private String title;

    private Money price;

}
