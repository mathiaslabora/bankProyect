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
        switch (transaction.getTransaction_type()) {
            case "Compra":
                return CardPurchase(transaction);
            case "Transferencia":
                return Transfer(transaction);
            case "Depósito":
                return Deposit(transaction);
            case "Retiro":
                return Withdrawal(transaction);
            case "PagoFactura":
                return BillPayment(transaction);
            default:
                return Mono.error(new UnsupportedOperationException("Tipo de transacción no especificada"));
        }
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



    private Mono<Transaction> CardPurchase(Transaction transaction) {
        if (transaction.getOrdinal() > 1) {
            Flux.range(1, transaction.getOrdinal())
                    .flatMap(i -> {
                        Transaction newTransaction = transaction;
                        newTransaction.setOrdinal(i.shortValue());
                        return Mono.just(transactionRepository.save(newTransaction));
                    });
        } else {
             Mono.just(transactionRepository.save(transaction));
        }

        return Mono.just(transactionRepository.save(transaction));
    }

    private Mono<Transaction> Transfer(Transaction transaction) {
        return Mono.just(transaction);
    }

    private Mono<Transaction> Deposit(Transaction transaction) {
        return Mono.just(transaction);
    }

    private Mono<Transaction> Withdrawal(Transaction transaction) {
        return Mono.just(transaction);
    }

    private Mono<Transaction> BillPayment(Transaction transaction) {
        return Mono.just(transaction);
    }
}
