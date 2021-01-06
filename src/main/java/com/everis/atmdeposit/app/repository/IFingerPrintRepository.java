package com.everis.atmdeposit.app.repository;

import com.everis.atmdeposit.app.model.Dummy;
import com.everis.atmdeposit.app.model.Person;
import reactor.core.publisher.Mono;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Repository IFingerPrintRepository.
 */
public interface IFingerPrintRepository {

    @POST(value = "/core/fingerprints/validate")
    Mono<Dummy> saveFingerPerson(@Body Person person);

}
