package com.sistemabancario.account.controller;

import com.sistemabancario.account.domain.Account;
import com.sistemabancario.account.service.IAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest
class AccountControllerTest {
	@Autowired
	WebTestClient webTestClient;

	@MockBean
	IAccountService accountService;
	List<Account> lAccount;
	Account account;

	static String ID = "1111111";


	@BeforeEach
	public void setUp(){
		lAccount = new ArrayList<>();
		account =  new Account("11111",1234L,1111L,"123333",111.50,134L,"","","","","");
		lAccount.add(account);

		account=  new Account("1119999",88888L,19999L,"18888",111999.50,139994L,"","","","","");
		lAccount.add(account);


	}

	@Test
	void testFindAll() {
		when(accountService.findAll()).thenReturn(Flux.fromIterable(lAccount));
		Flux<Account> responseBody = webTestClient.get()
				.uri("/account")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Account.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new Account("11111",1234L,1111L,"123333",111.50,134L,"","","","",""))
				.expectNext(new Account("1119999",88888L,19999L,"18888",111999.50,139994L,"","","","",""))
				.verifyComplete();
	}

	@Test
	void testGetById() {
		/*when(accountService.findById(ID)).thenReturn(Mono.just(account));
		webClient.get().uri("/{id}", 100)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.name").isNotEmpty()
				.jsonPath("$.id").isEqualTo(100)
				.jsonPath("$.name").isEqualTo("Test")
				.jsonPath("$.salary").isEqualTo(1000);

		Mockito.verify(repository, times(1)).findById(100);*/

	}

	@Test
	void testCreate() {
		System.out.println("Not yet implemented");
	}

	@Test
	void testUpdate() {
		System.out.println("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		System.out.println("Not yet implemented");
	}

}
