package com.nex3z.examples.spring.reactive.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Book {

    private Long id;

    private String title;

    private Long price;

}
