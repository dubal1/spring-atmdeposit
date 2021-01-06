package com.everis.atmdeposit.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtmDeposit {

    private String fingerprintEntityName;
    private List<Account> validAccounts;
    private Integer customerAmount;
}
