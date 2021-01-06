package com.everis.atmdeposit.app.repository;

import com.everis.atmdeposit.app.model.Account;
import reactor.core.publisher.Mono;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IAccountRepository {

    @GET(value = "/core/accounts")
    Mono<Account> getAccount(
            @Query(value = "cardNumber") String cardNumber);
}
