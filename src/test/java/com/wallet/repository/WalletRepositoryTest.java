package com.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Optional;

import com.wallet.entity.Wallet;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletRepositoryTest {
    private static final String NAME = "walletName";
    private static final BigDecimal VALUE = new BigDecimal(100);

    @Autowired
    WalletRepository walletRepository;

    @Before
    public void setUp(){
        Wallet w = new Wallet();
        w.setName(NAME);
        w.setValue(VALUE);
        walletRepository.save(w);
    }
    
    @After
    public void tearDown(){
        walletRepository.deleteAll();
    }

    @Test
    public void testSave(){
        Wallet w = new Wallet();
        w.setName("teste_name");
        w.setValue(new BigDecimal(50));

        Wallet response = walletRepository.save(w);
        assertNotNull(response);
        assertEquals(new BigDecimal(50), response.getValue());
    }

    @Test
    public void testFindByName(){
        Optional<Wallet> w = walletRepository.findByName(NAME);
        assertNotNull(w);
    }
}
