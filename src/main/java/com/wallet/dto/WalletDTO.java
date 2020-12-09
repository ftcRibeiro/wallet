package com.wallet.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class WalletDTO {
    private Long id;
    @Length(min=3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
    @NotNull(message = "O parâmetro name não pode ser nulo")
    private String name;
    @NotNull(message = "O parâmetro value não pode ser nulo")
    private BigDecimal value;
}
