package com.bank.bank.service;

import com.bank.bank.model.Transaction;
import com.bank.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService{
    private TransactionRepository transactionRepository;
    @Override
    public Flux<Transaction> getAllTransactions() {
        return Flux.fromIterable(transactionRepository.findAll());
    }

    @Override
    public Mono<Transaction> getTransactionById(int transactionId) {
        return Mono.justOrEmpty(transactionRepository.findById(Long.valueOf(transactionId)));
    }

    @Override
    public Mono<Transaction> newTransaction(Transaction transaction) {
        return Mono.just(transactionRepository.save(transaction));
    }

    @Override
    public Mono<Transaction> updateTransaction(Transaction transaction) {
        return Mono.just(transactionRepository.save(transaction));
    }

    @Override
    public Mono<Boolean> deleteTransaction(int transactionId) {
        return Mono.fromCallable(() -> {
            transactionRepository.deleteById(Long.valueOf(transactionId));
            return true;
        });
    }
}
