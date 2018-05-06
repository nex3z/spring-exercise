package com.nex3z.examples.simplerestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestApiApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init(UserRepository userRepository, NoteRepository noteRepository) {
//        return (evt) -> Arrays.asList("Lorem,Ipsum,Dolor,Sit,Amet".split(","))
//                .forEach(name -> {
//                    User user = userRepository.save(new User(name, "password"));
//                    noteRepository.save(new Note(user, name + "'s note 1"));
//                    noteRepository.save(new Note(user, name + "'s note 2"));
//                });
//    }
}
