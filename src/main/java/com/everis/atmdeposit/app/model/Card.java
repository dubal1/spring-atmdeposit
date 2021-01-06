package com.everis.atmdeposit.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model Card.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    /**
     * field cardNumber.
     */
    private String cardNumber;
    /**
     * field active.
     */
    private Boolean active;
    private Account account;
}
