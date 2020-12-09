package com.wallet.controller;

import javax.validation.Valid;

import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;
import com.wallet.response.Response;
import com.wallet.service.UserWalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("userwallet")
public class UserWalletController {

    @Autowired
    private UserWalletService userWalletService;

    @PostMapping
    public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result){
        Response<UserWalletDTO> response = new Response<UserWalletDTO>();

        if (result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        UserWallet userWallet = userWalletService.save(this.dtoToEntity(dto));
        response.setData(this.entityToDto(userWallet));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private UserWallet dtoToEntity(UserWalletDTO dto){
        UserWallet uw = new UserWallet();
        User u = new User();
        Wallet w = new Wallet();
        u.setId(dto.getUsers());
        w.setId(dto.getWallet());
        uw.setId(dto.getId());
        uw.setUsers(u);
        uw.setWallet(w);
        return uw;
    }

    private UserWalletDTO entityToDto(UserWallet uw){
        UserWalletDTO dto = new UserWalletDTO();
        dto.setId(uw.getId());
        dto.setUsers(uw.getUsers().getId());
        dto.setWallet(uw.getWallet().getId());
        return dto;
    }
}
