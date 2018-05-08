package com.nex3z.examples.simplerestapi.persistence.repository;

import com.nex3z.examples.simplerestapi.persistence.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUserName(String name);

}
