package com.nex3z.examples.simplerestapi.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nex3z.examples.simplerestapi.persistence.common.IEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data @EqualsAndHashCode(exclude = "user")
public class Note implements IEntity {

    @Id @GeneratedValue
    private Long id;

    @JsonIgnore @ManyToOne
    private User user;

    private String content;

    private Note() {}

    public Note(User user, String content) {
        this.user = user;
        this.content = content;
    }
}
