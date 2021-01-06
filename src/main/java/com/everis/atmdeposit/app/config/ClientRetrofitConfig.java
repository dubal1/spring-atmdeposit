package com.everis.atmdeposit.app.config;

import com.everis.atmdeposit.app.repository.IAccountRepository;
import com.everis.atmdeposit.app.repository.ICardRepository;
import com.everis.atmdeposit.app.repository.IFingerPrintRepository;
import com.everis.atmdeposit.app.repository.IPersonRepository;
import com.everis.atmdeposit.app.repository.IReniecRepository;
import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * config ClientRetrofitConfig.
 */
@Configuration
public class ClientRetrofitConfig {
    /**
     * @return repository.
     */
    @Bean
    IFingerPrintRepository fingerPrintRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9001")
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IFingerPrintRepository.class);
    }

    @Bean
    IReniecRepository reniecRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9002")
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IReniecRepository.class);
    }

    @Bean
    IPersonRepository personRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8001")
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IPersonRepository.class);
    }

    @Bean
    ICardRepository cardRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9003")
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ICardRepository.class);
    }

    @Bean
    IAccountRepository accountRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9004")
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IAccountRepository.class);
    }
}
