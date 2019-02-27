package com.example.vavr.tutorial.advance.spring.data;

import io.vavr.collection.Seq;
import io.vavr.control.Option;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    Option<User> findById(long id);
    Seq<User> findByName(String name);
    User save(User user);
}
