package com.everis.atmdeposit.app.controller;

import com.everis.atmdeposit.app.model.AtmDeposit;
import com.everis.atmdeposit.app.model.Dummy;
import com.everis.atmdeposit.app.model.Person;
import com.everis.atmdeposit.app.service.IATMDepositService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;

/**
 * ATMDepositController.
 */
@Slf4j
@RestController
public class ATMDepositController {

    @Autowired
    private IATMDepositService service;

    @PostMapping(value = "/atm/deposits")
    public Mono<ResponseEntity<Map<String, Object>>> validPerson(
            @Valid @RequestBody final Mono<Person> document) {
        Map<String, Object> objectMap = new HashMap<>();
        AtmDeposit atmResponse = new AtmDeposit();
        return document
                .flatMap(doc -> {
                    return service.savePerson(doc)
                            .filter(person -> !person.getBlacklist())
                            .flatMap(person -> validateFinger(person)
                                    .flatMap(dummy -> {
                                        atmResponse.setFingerprintEntityName(dummy.getEntityName());
                                        return service.getAllCards().collect(Collectors.toList());
                                    })
                                    .flatMap(allcards -> this.service.getAccounts(allcards))
                                    .flatMap(accounts -> {
                                        double total = accounts.stream()
                                                .mapToDouble(value -> value.getAmount())
                                                .sum();
                                        atmResponse.setCustomerAmount(total);
                                        atmResponse.setValidAccounts(accounts);
                                        return Mono.just(atmResponse);
                                    }))
                            .map(atmDeposit -> {
                                objectMap.put("atmDeposit", atmDeposit);
                                return new ResponseEntity<>(objectMap, HttpStatus.OK);
                            });
                })
                .onErrorResume(throwable -> {
                    return Mono.just(throwable).cast(WebExchangeBindException.class)
                            .flatMap(e -> Mono.just(e.getFieldErrors()))
                            .flatMapMany(Flux::fromIterable)
                            .map(FieldError::getDefaultMessage)
                            .collectList()
                            .flatMap(listErrors -> {
                                objectMap.put("err", listErrors);
                                objectMap.put("dateTime", new Date());
                                return Mono.just(new ResponseEntity<>(objectMap, HttpStatus.BAD_REQUEST));
                            });
                });

    }

    private Mono<Dummy> validateFinger(Person person) {
        if (person.getFingerprint()) {
            return service.saveFingerPrint(person);
        } else {
            return service.saveReniec(person);
        }
    }
}