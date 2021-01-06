package com.everis.atmdeposit.app.service.impl;

import com.everis.atmdeposit.app.model.Dummy;
import com.everis.atmdeposit.app.model.Person;
import com.everis.atmdeposit.app.repository.IFingerPrintRepository;
import com.everis.atmdeposit.app.service.IATMDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ATMDepositServiceImpl implements IATMDepositService {

    @Autowired
    private IFingerPrintRepository repository;

    @Override
    public Mono<Dummy> savePerson(Person person) {
        return repository.savePerson(person);
    }
}
