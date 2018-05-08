package com.nex3z.examples.simplerestapi.service;

import com.nex3z.examples.simplerestapi.persistence.model.Note;
import com.nex3z.examples.simplerestapi.service.common.IService;

import java.util.List;

public interface INoteService extends IService<Note> {

    List<Note> findByUserName(final String userName);

}
