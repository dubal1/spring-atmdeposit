package com.everis.atmdeposit.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    /**
     * field accountNumber.
     */
    private String accountNumber;
    /**
     * field amount.
     */
    private Integer amount;
}
