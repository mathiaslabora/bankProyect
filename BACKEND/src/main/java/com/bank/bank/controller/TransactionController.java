package com.bank.bank.controller;

import com.bank.bank.model.Transaction;
import com.bank.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/getAll")
    public Flux<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{transactionId}")
    public Mono<ResponseEntity<Transaction>> getTransactionById(@PathVariable int transactionId) {
        return transactionService.getTransactionById(transactionId)
                .map(transaction -> ResponseEntity.ok(transaction))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public Mono<ResponseEntity<Transaction>> newTransaction(@RequestBody Transaction transaction) {
        return transactionService.newTransaction(transaction)
                .map(newTransaction -> ResponseEntity.status(HttpStatus.CREATED).body(newTransaction));
    }

    @PutMapping("/updateTransaction")
    public Mono<ResponseEntity<Transaction>> updateTransaction(@RequestBody Transaction transaction) {
        return transactionService.getTransactionById(transaction.getTransaction_number())
                .flatMap(existingTransaction -> {
                    existingTransaction.setDate_trans(transaction.getDate_trans());
                    existingTransaction.setAmount(transaction.getAmount());
                    existingTransaction.setAlert(transaction.getAlert());
                    existingTransaction.setDestination_account(transaction.getDestination_account());
                    existingTransaction.setDescription(transaction.getDescription());
                    existingTransaction.setCurrency(transaction.getCurrency());
                    existingTransaction.setTransaction_type(transaction.getTransaction_type());
                    existingTransaction.setOrdinal(transaction.getOrdinal());
                    return transactionService.updateTransaction(existingTransaction);
                })
                .map(updatedTransaction -> ResponseEntity.ok(updatedTransaction))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{transactionId}")
    public Mono<ResponseEntity<Void>> deleteTransaction(@PathVariable int transactionId) {
        return transactionService.deleteTransaction(transactionId)
                .map(deleted -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}