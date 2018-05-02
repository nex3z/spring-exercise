package com.nex3z.examples.simplerestapi.persistence.repository;

import com.nex3z.examples.simplerestapi.persistence.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Collection<Note> findByUserName(String name);

}
