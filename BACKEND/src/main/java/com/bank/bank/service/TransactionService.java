package com.bank.bank.service;

import com.bank.bank.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Flux<Transaction> getAllTransactions();
    Mono<Transaction> getTransactionById(int transactionId);
    Mono<Transaction> newTransaction(Transaction transaction);
    Mono<Transaction> updateTransaction(Transaction transaction);
    Mono<Boolean> deleteTransaction(int transactionId);
}
