package com.nex3z.examples.simplerestapi.service.impl;

import com.nex3z.examples.simplerestapi.persistence.model.Note;
import com.nex3z.examples.simplerestapi.persistence.repository.NoteRepository;
import com.nex3z.examples.simplerestapi.service.INoteService;
import com.nex3z.examples.simplerestapi.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl extends AbstractService<Note> implements INoteService {

    private final NoteRepository noteRepository;

    @Autowired
    NoteServiceImpl(NoteRepository userRepository) {
        super();
        this.noteRepository = userRepository;
    }

    @Override
    protected JpaRepository<Note, Long> getDao() {
        return noteRepository;
    }

    @Override
    public List<Note> findByUserName(String userName) {
        return noteRepository.findByUserName(userName);
    }
}
