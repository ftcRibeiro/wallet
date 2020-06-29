package com.wallet.service.impl;

import java.util.Optional;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;
import com.wallet.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
	@Override
	public User save(User u) {
        
		return userRepository.save(u);
	}

	@Override
	public Optional<User> findByEmail(String email) {

		return userRepository.findByEmail(email);
	}
    
}