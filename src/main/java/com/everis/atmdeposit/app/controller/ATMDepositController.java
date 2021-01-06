package com.everis.atmdeposit.app.controller;

import com.everis.atmdeposit.app.model.Dummy;
import com.everis.atmdeposit.app.model.Person;
import com.everis.atmdeposit.app.service.IATMDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ATMDepositController {

    @Autowired
    private IATMDepositService service;

    @PostMapping(value = "/atm/deposits")
    public Mono<Dummy> validPerson(@RequestBody final Person person) {
        return service.savePerson(person);
    }
}
