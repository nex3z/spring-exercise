package com.nex3z.examples.simplerestapi.controller;

import com.nex3z.examples.simplerestapi.controller.common.AbstractController;
import com.nex3z.examples.simplerestapi.controller.exception.ResourceNotFoundException;
import com.nex3z.examples.simplerestapi.controller.util.RestPrecondition;
import com.nex3z.examples.simplerestapi.persistence.model.Note;
import com.nex3z.examples.simplerestapi.persistence.model.User;
import com.nex3z.examples.simplerestapi.persistence.repository.NoteRepository;
import com.nex3z.examples.simplerestapi.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/{userName}/notes")
public class NoteRestController extends AbstractController<Note> {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    @Autowired
    public NoteRestController(NoteRepository noteRepository, UserRepository userRepository) {
        super(Note.class);
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    protected JpaRepository<Note, Long> getRepository() {
        return noteRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Note> getNotes(@PathVariable String userName) {
        validateUser(userName);
        return noteRepository.findByUserName(userName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{noteId}")
    Note getNote(@PathVariable String userName, @PathVariable Long noteId) {
        User user = validateUser(userName);
        Note note = findOneInternal(noteId);
        if (note.getUser().getId().equals(user.getId())) {
            return note;
        } else {
            throw new ResourceNotFoundException("Note with id " + noteId + " not found");
        }
    }

    private User validateUser(String userName) {
        return RestPrecondition.checkIsPresent(this.userRepository.findByName(userName), "User " + userName + "not found");
    }
}
