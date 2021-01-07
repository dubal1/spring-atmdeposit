package com.everis.atmdeposit.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.everis.atmdeposit.app.model.Account;
import com.everis.atmdeposit.app.model.Card;
import com.everis.atmdeposit.app.model.Dummy;
import com.everis.atmdeposit.app.model.Person;
import com.everis.atmdeposit.app.repository.*;
import com.everis.atmdeposit.app.service.impl.ATMDepositServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ATMDepositSermiceImplTest {

    @Mock
    private IFingerPrintRepository fingerPrintRepository;

    @Mock
    private IReniecRepository reniecRepository;

    @Mock
    private IPersonRepository personRepository;

    @Mock
    private ICardRepository cardRepository;

    @Mock
    private IAccountRepository accountRepository;

    @InjectMocks
    private ATMDepositServiceImpl service;

    @Test
    public void saveFingerPrint() {
        Dummy newDummy = new Dummy();
        newDummy.setEntityName("Core");
        newDummy.setSuccess(Boolean.TRUE);
        when(fingerPrintRepository.saveFingerPerson(any())).thenReturn(Mono.just(newDummy));
        Person person = new Person();
        person.setBlacklist(false);
        person.setDocument("12345678");
        person.setFingerprint(true);
        Dummy response = this.service.saveFingerPrint(person).block();

        assertEquals("Core", response.getEntityName());
        assertEquals(true, response.getSuccess());
    }

    @Test
    public void saveReniec() {
        Dummy newDummy = new Dummy();
        newDummy.setEntityName("Reniec");
        newDummy.setSuccess(Boolean.TRUE);
        when(reniecRepository.saveReniec(any())).thenReturn(Mono.just(newDummy));
        Person person = new Person();
        person.setBlacklist(false);
        person.setDocument("12345679");
        person.setFingerprint(true);
        Dummy response = this.service.saveReniec(person).block();
        assertEquals("Reniec", response.getEntityName());
        assertEquals(true, response.getSuccess());
    }

    @Test
    public void savePerson_Case1() {
        Person person = new Person();
        person.setId("1");
        person.setDocument("10000000");
        person.setFingerprint(true);
        person.setBlacklist(false);
        when(personRepository.savePerson(any())).thenReturn(Mono.just(person));
        Person response = this.service.savePerson(person).block();
        assertEquals("1", response.getId());
        assertEquals("10000000", response.getDocument());
        assertEquals(true, response.getFingerprint());
        assertEquals(false, response.getBlacklist());
    }

    @Test
    public void savePerson_Case2() {
        Person person = new Person();
        person.setId("2");
        person.setDocument("10000001");
        person.setFingerprint(false);
        person.setBlacklist(false);
        when(personRepository.savePerson(any())).thenReturn(Mono.just(person));
        Person response = this.service.savePerson(person).block();
        assertEquals("2", response.getId());
        assertEquals("10000001", response.getDocument());
        assertEquals(false, response.getFingerprint());
        assertEquals(false, response.getBlacklist());
    }

    @Test
    public void savePerson_Case3() {
        Person person = new Person();
        person.setId("3");
        person.setDocument("10000002");
        person.setFingerprint(true);
        person.setBlacklist(true);
        when(personRepository.savePerson(any())).thenReturn(Mono.just(person));
        Person response = this.service.savePerson(person).block();
        assertEquals("3", response.getId());
        assertEquals("10000002", response.getDocument());
        assertEquals(true, response.getFingerprint());
        assertEquals(true, response.getBlacklist());
    }

    @Test
    public void getAllCards() {
        when(cardRepository.getCards2()).thenReturn(Flux.just(buildListCards()));
        List<Card> response = this.service.getAllCards().collectList().block();
        assertEquals("1111222233334441", response.get(0).getCardNumber());
        assertEquals(true, response.get(0).getActive());
    }

    @Test
    public void getAccounts() {
        Account account =  new Account();
        account.setAccountNumber("1111222233334441xxx");
        account.setAmount(100.0);
        when(accountRepository.getAccount(any())).thenReturn(Mono.just(account));
        List<Account> response =  this.service.getAccounts(buildListCards()).block();
        assertEquals(100.0,response.get(0).getAmount());
        assertEquals("1111222233334441xxx",response.get(0).getAccountNumber());
    }


    private List<Card> buildListCards() {
        Card card001 = new Card();
        card001.setCardNumber("1111222233334441");
        card001.setActive(true);

        Card card002 = new Card();
        card002.setCardNumber("1111222233334442");
        card002.setActive(true);

        Card card003 = new Card();
        card003.setCardNumber("1111222233334443");
        card003.setActive(true);

        Card card004 = new Card();
        card004.setCardNumber("1111222233334444");
        card004.setActive(false);

        Card card005 = new Card();
        card005.setCardNumber("1111222233334445");
        card005.setActive(false);

        Card card006 = new Card();
        card006.setCardNumber("1111222233334446");
        card006.setActive(true);

        List<Card> listCards = new ArrayList<>();
        listCards.add(card001);
        listCards.add(card002);
        listCards.add(card003);
        listCards.add(card004);
        listCards.add(card005);
        listCards.add(card006);
        return listCards;
    }
}
