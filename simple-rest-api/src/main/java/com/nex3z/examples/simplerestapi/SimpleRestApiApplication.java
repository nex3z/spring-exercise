package com.nex3z.examples.simplerestapi;

import com.nex3z.examples.simplerestapi.persistence.repository.NoteRepository;
import com.nex3z.examples.simplerestapi.persistence.repository.UserRepository;
import com.nex3z.examples.simplerestapi.persistence.model.Note;
import com.nex3z.examples.simplerestapi.persistence.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SimpleRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestApiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, NoteRepository noteRepository) {
        return (evt) -> Arrays.asList("Lorem,Ipsum,Dolor,Sit,Amet".split(","))
                .forEach(name -> {
                    User user = userRepository.save(new User(name, "password"));
                    noteRepository.save(new Note(user, name + "'s note 1"));
                    noteRepository.save(new Note(user, name + "'s note 2"));
                });
    }
}
