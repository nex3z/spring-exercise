package com.nex3z.examples.simplerestapi.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @Data
public class User {

    @Id @GeneratedValue
    private Long id;

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