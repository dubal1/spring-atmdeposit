package com.everis.atmdeposit.app.service;

import com.everis.atmdeposit.app.model.Dummy;
import com.everis.atmdeposit.app.model.Person;
import reactor.core.publisher.Mono;

public interface IATMDepositService {

    Mono<Dummy> savePerson(Person person);
}
