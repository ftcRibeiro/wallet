package com.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.math.BigDecimal;
import java.util.Date;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletItemRepositoryTest{

    private static final Date DATE = new Date();
    private static final TypeEnum TYPE = TypeEnum.EN;
    private static final String DESCRIPTION = "Conta de Luz";
    private static final BigDecimal VALUE = BigDecimal.valueOf(65);
    private Long savedWalletId = null;
    private Long savedWalletItemId = null;

    @Autowired
    WalletItemRepository walletItemRepository;

    @Autowired WalletRepository walletRepository;

    @Before
    public void setUp(){
        Wallet w = new Wallet();
        w.setName("Carteira de Teste");
        w.setValue(BigDecimal.valueOf(300));
        walletRepository.save(w);

        WalletItem wi = new WalletItem(null, w, DATE, TYPE, DESCRIPTION, VALUE);
        walletItemRepository.save(wi);
        savedWalletItemId = wi.getId();
        savedWalletId = w.getId();
    }

    @After
    public void tearDown(){
        walletRepository.deleteAll();
        walletItemRepository.deleteAll();
    }

    @Test
    public void testSave(){
        Wallet w = new Wallet();
        w.setName("Carteira 1");
        w.setValue(BigDecimal.valueOf(500));

        walletRepository.save(w);

        WalletItem wi = new WalletItem(1L,w,DATE, TYPE, DESCRIPTION, VALUE);
        WalletItem response = walletItemRepository.save(wi);
        assertNotNull(response);
        assertEquals(response.getDescription(), DESCRIPTION);
        assertEquals(response.getType(), TYPE);
        assertEquals(response.getValue(), VALUE);
        assertEquals(response.getWallet().getId(), w.getId());
    }

    @Test
    public void testSaveInvalidWalletItem(){}
}