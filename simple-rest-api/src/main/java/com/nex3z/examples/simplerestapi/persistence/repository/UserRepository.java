package com.nex3z.examples.simplerestapi.persistence.repository;

import com.nex3z.examples.simplerestapi.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

}
