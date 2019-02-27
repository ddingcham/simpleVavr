package com.example.vavr.tutorial.advance;

import com.example.vavr.tutorial.advance.spring.data.TestApplication;
import com.example.vavr.tutorial.advance.spring.data.User;
import com.example.vavr.tutorial.advance.spring.data.UserRepository;
import io.vavr.collection.Seq;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class SpringDataSupportTest {

    private static final String USER_NAME = "John";
    private static final int NUM_OF_USERS = 10;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        for (int i = 0; i < NUM_OF_USERS; i++) {
            userRepository.save(new User(USER_NAME));
        }
    }

    @Test
    public void whenAddUsers_thenGetUsers() {
        assertFalse(userRepository.findById(1L).isEmpty());
        assertTrue(userRepository.findById(100L).isEmpty());

        Seq<User> users = userRepository.findByName(USER_NAME);
        assertEquals(NUM_OF_USERS, users.size());
    }
}
