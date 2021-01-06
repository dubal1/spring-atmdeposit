package com.everis.atmdeposit.app.repository;

import com.everis.atmdeposit.app.model.Person;
import reactor.core.publisher.Mono;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Repository IPersonRepository.
 */
public interface IPersonRepository {

    @POST(value = "/core/persons")
    Mono<Person> savePerson(@Body Person person);
}
