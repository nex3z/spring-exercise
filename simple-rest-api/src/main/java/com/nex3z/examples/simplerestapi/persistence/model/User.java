package com.nex3z.examples.simplerestapi.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nex3z.examples.simplerestapi.persistence.common.IEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @Data @EqualsAndHashCode(exclude = "notes")
public class User implements IEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true, length = 64, nullable = false)
    private String name;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user") @JsonIgnore
    private Set<Note> notes = new HashSet<>();

    private User() {}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
