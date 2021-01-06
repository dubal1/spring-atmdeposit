package com.everis.atmdeposit.app.service.impl;

import com.everis.atmdeposit.app.model.Account;
import com.everis.atmdeposit.app.model.Card;
import com.everis.atmdeposit.app.model.Dummy;
import com.everis.atmdeposit.app.model.Person;
import com.everis.atmdeposit.app.repository.*;
import com.everis.atmdeposit.app.service.IATMDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ATMDepositServiceImpl implements IATMDepositService {

    @Autowired
    private IFingerPrintRepository fingerPrintRepository;

    @Autowired
    private IReniecRepository reniecRepository;

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private ICardRepository cardRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Mono<Dummy> saveFingerPrint(Person document) {
        return fingerPrintRepository.saveFingerPerson(document);
    }

    @Override
    public Mono<Dummy> saveReniec(Person document) {
        return reniecRepository.saveReniec(document);
    }

    @Override
    public Mono<Person> savePerson(Person document) {
        return personRepository.savePerson(document);
    }

    @Override
    public Flux<Card> getAllCards() {
        return cardRepository.getCards2()
                .flatMap(cards -> Flux.fromIterable(cards)
                        .filter(card -> card.getActive()));
    }

    @Override
    public Mono<Account> getAccounts(String cardNumber) {
        return accountRepository.getAccount(cardNumber);
    }
}
