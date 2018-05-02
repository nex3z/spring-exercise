package com.nex3z.examples.simplerestapi.persistence.controller;

import com.nex3z.examples.simplerestapi.persistence.model.Note;
import com.nex3z.examples.simplerestapi.persistence.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("notes")
public class NoteController {

    private NoteRepository noteRepository;

    @Autowired
    NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Note> getNotes() {
        return noteRepository.findAll();
    }

}
