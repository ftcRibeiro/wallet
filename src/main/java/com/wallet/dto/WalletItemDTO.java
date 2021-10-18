package com.wallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class WalletItemDTO {

    private Long id;
    
    @NotNull(message = "Insira uma carteira válida")
    private Long wallet;

    @NotNull(message = "Insira uma data válida")
    private Date date;

    @NotNull(message = "Insira um tipo de carteira")
    @Pattern(regexp = "^(ENTRADA|SAÍDA)$", message = "Para o campo de tipo são aceitos somente ENTRADA ou SAÍDA")
    private String type;

    @NotNull(message = "A descrição não pode ser vazia")
    @Length(min = 5, message = "A descrição deve possuir pelo menos 5 caracteres")
    private String description; 

    @NotNull(message = "Insira um valor para a carteira")
    private BigDecimal value;
}
