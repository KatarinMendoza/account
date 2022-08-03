package com.sistemabancario.account.controller;

import com.sistemabancario.account.domain.Account;
import com.sistemabancario.account.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    private static final Logger LOGGER= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @GetMapping
    public Flux<Account> findAll(){
        LOGGER.info("getAll" + "OK");
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Account> getById(@PathVariable("id") String id){
        LOGGER.info("getById" + "OK");
        return accountService.findById(id);
    }

    @PostMapping
    public Mono<Account> create(@RequestBody Account account){
        LOGGER.info("create" + "OK");
        return accountService.save(account);
    }

    @PutMapping
    public Mono<Account> update(@RequestBody Account account){
        LOGGER.info("update" + "OK");
        return accountService.update(account);
    }
    @DeleteMapping
    public Mono<Void> deleteById(@PathVariable("id") String id){
        LOGGER.info("deleteById" + "OK");
        return accountService.deleteById(id);
    }

    @GetMapping("/Consultas/{document}")
    public Flux<Account> findByClient(String document){
        LOGGER.info("FindByClient" + "OK");
        return accountService.findByClient(document);
    }

}
