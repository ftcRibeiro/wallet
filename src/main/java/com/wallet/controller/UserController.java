package com.wallet.controller;

import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.response.Response;
import com.wallet.service.UserService;
import com.wallet.util.Bcrypt;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "UserController", description = "Operations pertaining to User")
@RequestMapping("user")
public class UserController {
    
    @Autowired
    UserService userService;
    

    @ApiOperation(value = "Create a new User", response = Response.class)
    @PostMapping
    public ResponseEntity<Response<UserDTO>> create(@Valid @RequestBody UserDTO dto, BindingResult result){
        
        Response<UserDTO> response = new Response<UserDTO>();

        if (result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        
        User user = userService.save(this.dtoToEntity(dto));
        response.setData(this.entityToDto(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private User dtoToEntity(UserDTO dto){
        User u = new User();
        u.setId(dto.getId());
        u.setEmail(dto.getEmail());
        u.setName(dto.getName());
        u.setPassword(Bcrypt.getHash(dto.getPassword()));
        return u;
    }

    private UserDTO entityToDto(User u){
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setEmail(u.getEmail());
        dto.setName(u.getName());
        return dto;
    }

}
