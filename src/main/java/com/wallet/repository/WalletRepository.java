package com.wallet.repository;

import java.util.Optional;
import com.wallet.entity.Wallet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository <Wallet, Long> {
    
    Optional<Wallet> findById(Long id);

    Optional<Wallet> findByName(String name);
}
