package com.nex3z.examples.data.jdbc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String name;

}
