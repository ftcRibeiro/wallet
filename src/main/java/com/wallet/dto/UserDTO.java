package com.wallet.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Email;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDTO {
 
    private Long id;
    @NotEmpty
    @Email(message = "Email inválido")
    private String email;
    @Length(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
    private String name;
    @NotNull
    @Length(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    private String password;

}