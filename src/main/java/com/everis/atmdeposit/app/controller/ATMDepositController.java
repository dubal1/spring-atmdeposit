package com.everis.atmdeposit.app.controller;

import com.everis.atmdeposit.app.model.*;
import com.everis.atmdeposit.app.service.IATMDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
public class ATMDepositController {

    @Autowired
    private IATMDepositService service;

    @PostMapping(value = "/atm/deposits")
    public Mono<?> validPerson(@RequestBody final Person document) {
        return service.savePerson(document)
                .filter(person -> !person.getBlacklist())
                .flatMap(person -> person.getFingerprint() ?
                        service.saveFingerPrint(person) : service.saveReniec(person))
                .map(dummy -> new AtmDeposit(dummy.getEntityName(), Arrays.asList(
                        new Account("123456789101", 500),
                        new Account("123456781230", 1500))
                        , 2000));
    }
}
