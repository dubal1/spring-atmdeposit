package com.everis.atmdeposit.app.repository;

import com.everis.atmdeposit.app.model.Card;
import reactor.core.publisher.Flux;
import retrofit2.http.GET;

import java.util.List;

/**
 * Repository ICardRepository.
 */
public interface ICardRepository {

    @GET(value = "/core/cards2")
    Flux<List<Card>> getCards2();
}
