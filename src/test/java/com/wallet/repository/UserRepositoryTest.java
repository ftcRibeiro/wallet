package com.wallet.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import com.wallet.entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    private static final String EMAIL = "user@email.com";
    private static final String NAME = "user";
    private static final String PSW = "password";


    @Autowired
    UserRepository repository;

    @Before
    public void setUp(){
        User u = new User();
        u.setName(NAME);
        u.setPassword(PSW);
        u.setEmail(EMAIL);

        repository.save(u);
    }

    @After
    public void tearDown(){
        repository.deleteAll();
    }

    @Test
    public void testSave(){
        User u = new User();
        u.setName("username");
        u.setPassword("password");
        u.setEmail("username@email.com");

        User response = repository.save(u);
        assertNotNull(response);
    }

    @Test
    public void testFindByEmail(){
        Optional<User> response = repository.findByEmail(EMAIL);
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(),EMAIL);
    }
}