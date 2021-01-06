package com.everis.atmdeposit.app.controller;

import com.everis.atmdeposit.app.model.Account;
import com.everis.atmdeposit.app.model.AtmDeposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
/*
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)*/
@SpringBootTest
public class AtmDepositControllerTest {
    /*
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebTestClient client;

    @BeforeEach
    private void setUp() {
        client = WebTestClient
                .bindToApplicationContext(applicationContext)
                .configureClient()
                .baseUrl("/atm")
                .build();
    }

    @Test
    private void findAll() {

        AtmDeposit atmDeposit = new AtmDeposit("Core", Arrays.asList(
                new Account("5527023119095755XXX", 1000.0),
                new Account("5267072725200626XXX", 500.0),
                new Account("5147562525936540XXX", 1500.0)),
                3000.0);

        client.post()
                .uri("/deposits")
                .body(Mono.just(atmDeposit), AtmDeposit.class)
                .exchange()
                .expectStatus().isOk();

    }*/
}
