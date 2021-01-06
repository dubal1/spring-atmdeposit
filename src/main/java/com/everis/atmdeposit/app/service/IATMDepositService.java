package com.everis.atmdeposit.app.service;

import com.everis.atmdeposit.app.model.Account;
import com.everis.atmdeposit.app.model.Card;
import com.everis.atmdeposit.app.model.Dummy;
import com.everis.atmdeposit.app.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IATMDepositService {

    Mono<Dummy> saveFingerPrint(Person document);
    Mono<Dummy> saveReniec(Person document);
    Mono<Person> savePerson(Person person);
    Flux<Card> getAllCards();
    Mono<Account> getAccounts(String cardNumber);
}
