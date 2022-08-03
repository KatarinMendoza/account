package com.sistemabancario.account.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@AllArgsConstructor
@Data
@Document("account")
public class Account {
    @Id
    private String id;
    private String clientId;
    private String accountTypeId;
    private String nroCuenta;
    private Double saldo;
    private String representationId;
    private String documentNumber;
    private String phoneNumber;
    private String email;
    private String documentTypeId;
    private String cointypeId;
}
