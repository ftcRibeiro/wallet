package com.wallet.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import com.wallet.repository.UserRepository;
import com.wallet.entity.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    private static final String EMAIL = "user@email.com";
    private static final String USER = "user";
    private static final String PSW = "password";

    @Autowired
    UserService service;
    
    @MockBean
    UserRepository repository;

    @Before
    public void setUp(){
        BDDMockito.given(repository.findByEmail(Mockito.anyString())).willReturn(Optional.of(new User()));
    }
    
    @Test
    public void testFindByEmail(){
        Optional<User> user = service.findByEmail(EMAIL);
        assertTrue(user.isPresent());
    }
}