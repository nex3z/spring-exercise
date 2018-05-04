package com.nex3z.examples.simplerestapi.persistence.controller;

import com.nex3z.examples.simplerestapi.persistence.controller.exception.ResourceNotFoundException;
import com.nex3z.examples.simplerestapi.persistence.controller.util.Precondition;
import com.nex3z.examples.simplerestapi.persistence.model.Note;
import com.nex3z.examples.simplerestapi.persistence.model.User;
import com.nex3z.examples.simplerestapi.persistence.repository.NoteRepository;
import com.nex3z.examples.simplerestapi.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/{userName}/notes")
public class NoteRestController {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    @Autowired
    public NoteRestController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Note> getNotes(@PathVariable String userName) {
        validateUser(userName);
        return noteRepository.findByUserName(userName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{noteId}")
    Note getNote(@PathVariable String userName, @PathVariable Long noteId) {
        User user = validateUser(userName);
        Note note = this.noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note with id " + noteId + " not found"));
        if (note.getUser().getId().equals(user.getId())) {
            return note;
        } else {
            throw new ResourceNotFoundException("Note with id " + noteId + " not found");
        }
    }

    private User validateUser(String userName) {
        return Precondition.checkIsPresent(this.userRepository.findByName(userName), "User " + userName + "not found");
    }
}
