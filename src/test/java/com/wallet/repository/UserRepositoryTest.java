package com.wallet.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;

import java.util.Optional;

import com.wallet.entity.User;

import org.aspectj.apache.bcel.Repository;
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
    private static final String USER = "user";
    private static final String PSW = "password";


    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp(){
        User u = new User();
        u.setName(USER);
        u.setPassword(PSW);
        u.setEmail(EMAIL);

        userRepository.save(u);
    }

    @After
    public void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void testSave(){
        User u = new User();
        u.setName("username");
        u.setPassword("password");
        u.setEmail("user@email.com");

        User response = userRepository.save(u);
        assertNotNull(response);
    }

    @Test
    public void testFindByEmail(){
        Optional<User> response = userRepository.findByEmail(EMAIL);
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(),EMAIL);
    }
}