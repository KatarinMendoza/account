package com.sistemabancario.account.service.impl;

import com.sistemabancario.account.domain.Account;
import com.sistemabancario.account.domain.Client;
import com.sistemabancario.account.repository.IAccountRepository;
import com.sistemabancario.account.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
	@Autowired
    private final IAccountRepository accountRepository;
    @Override
    public Flux<Account> findAll() {return accountRepository.findAll();}

    @Override
    public Mono<Account> findById(String id) {return accountRepository.findById(id);}

    @Override
    public Mono<Account> save(Account account) {

        if(clientExit(account.getClientId()) == true){
            account.setDocumentNumber("");
            account.setEmail("");
            account.setPhoneNumber("");
        }else {
            if (account.getDocumentNumber().equals("") ||
                    account.getEmail().equals("") ||
                    account.getPhoneNumber().equals("")){
                System.out.println("Ingresar datos solicitados!!");
            }
        }

    	/*Mono<Client> client = iClientRepository.findById(account.getClient().getId());
    	accountRepository.findAll().filter(x-> x.getClient().getId().equals(account.getClient().getId()))
    								.flatMap(_account -> 
    									clienteService.validateClientType(client.map(x->x.getName()))
    										);*/

    	/*iClientRepository
    	.findById(account.getClient().getId())
    	.map(client -> clienteService.validateClientType(client.getName()).map(clientValidator ->
    	          clientValidator.Validator(findAll(x-> x.getClient().getId().equals(account.getClient().getId())))))
    	.mapflat(accunt_ -> clientValidator.Validator(findAll(x-> x.getClient().getId().equals(account.getClient().getId()))));
    	.flatMap(x -> {
    		Flux<IClientValidator> iClientValidator = Mono.just(x.getClientType().getId().get()) ->{
    			return 
    		}
    	})*/
    	//obtener cliente
    	//
    	//obtener cuentas del cliente existentes
    	//obtener


    	return accountRepository.save(account);
    }

    @Override
    public Mono<Account> update(Account account) {return accountRepository.save(account);}

    @Override
    public Mono<Void> deleteById(String id) {return accountRepository.deleteById(id);}

    @Override
    public Flux<Account> findByClient(String client) {
        return accountRepository.findAll()
                .filter(x->x.getClientId().equals(client));
    }

    @Override
    public Flux<GroupedFlux<String, Account>> reportByClient(String document) {
        RestTemplate temp=new RestTemplate();
        Client client = temp.getForObject("http://localhost:8030/client/Consultas/" + document, Client.class);
        return accountRepository.findAll()
                .filter(x->x.getClientId().equals(client.getId()))
                .groupBy(x->x.getAccountTypeId());
    }

    @Override
    public Double viewCredit(String id) {
     //   var credit = 0.0;
     // return   accountRepository.findById(id)
     //           .map(x->{
     //            return  credit = x.getSaldo();
     //           });
        return null;
    }
    public boolean clientExit(String id){
        RestTemplate temp=new RestTemplate();
        Client client = temp.getForObject("http://localhost:8030/client/" + id, Client.class);
        if(client.getId().length() != 0){
            return true;
        }
        return false;
    }
}
