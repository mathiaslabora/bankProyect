package com.bank.bank.service;

import com.bank.bank.model.Balance;
import com.bank.bank.model.Transaction;
import com.bank.bank.repository.BalanceRepository;
import com.bank.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    private BalanceService balanceService;
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
        /*
        esta funcion lo que hace es fijarse en base a la moneda si cuenta con saldo,
        en caso de que si procede a crear la transaccion, y si es compra en cuotas que viene marcado por el ordinal,
        genera la cantidad de transacciones que se indica, estas quedan identificadas de 1 a la cuota final en el ordinal y
        quedan por defecto como no procesadas. al momento del cobro se tomara por ordinal para ir cobrando las cuotas
         */
        Balance balance = balanceRepository.findById(Long.valueOf(transaction.getCuenta().getAccount_number()))
                .orElseThrow(() -> new RuntimeException("No se encontró en Saldo esa cuenta"));
        double amount;
        if (transaction.getCurrency() == 1) {//0 pesos, 1 dolares
            amount = Integer.parseInt(transaction.getAmount().toString()) * 38;//TODO crear cotizacion en BD
        } else {
            amount = Integer.parseInt(transaction.getAmount().toString());
        }
        if (balance.getOverdraft_limit().compareTo(BigDecimal.valueOf(amount)) >= 0) {
            if (transaction.getOrdinal() > 1) {
                Flux.range(1, transaction.getOrdinal())
                        .flatMap(i -> {
                            Transaction newTransaction = transaction;
                            newTransaction.setOrdinal(i.shortValue());
                            Mono.just(balanceService.updateBalance(balance));
                            return Mono.just(transactionRepository.save(newTransaction));
                        });
            } else {
                Mono.just(balanceService.updateBalance(balance));
                return Mono.just(transactionRepository.save(transaction));
            }

        } else {
            return Mono.error(new Exception("El monto excede el límite permitido"));
        }
        return Mono.error(new Exception("No se encontro saldo para esa cuenta"));
    }

    private Mono<Transaction> Transfer(Transaction transaction) {
        //Realizo la transf si tiene saldo y en base a la moneda, una vez controlado se descuentan de los saldos.


        int accountNum = transaction.getCuenta().getAccount_number();
        int accountDestination = transaction.getDestination_account();

        Balance balanceOrigin = balanceRepository.findById(Long.valueOf(accountNum)).orElseThrow(()-> new RuntimeException("Cuenta no se encuentra en Saldo"));
        Balance balanceDestination = balanceRepository.findById(Long.valueOf(accountDestination)).orElseThrow(()-> new RuntimeException("Cuenta destino no se encuentra en Saldo"));

        if (transaction.getCurrency() == 1) {//0 pesos, 1 dolares
            if (balanceOrigin.getAmount_dollars().compareTo(transaction.getAmount()) >=0){
                balanceOrigin.setAmount_dollars(balanceOrigin.getAmount_dollars().subtract(transaction.getAmount()));
                balanceService.updateBalance(balanceOrigin);
                balanceDestination.setAmount_dollars(balanceOrigin.getAmount_dollars().add(transaction.getAmount()));
                balanceService.updateBalance(balanceDestination);
                return Mono.just(transactionRepository.save(transaction));
            }else {
                return Mono.error(new Exception("El saldo no es suficiente para realizar transferencia"));
            }

        } else {
            if (balanceOrigin.getAmount_pesos().compareTo(transaction.getAmount()) >=0){
            balanceOrigin.setAmount_pesos(balanceOrigin.getAmount_pesos().subtract(transaction.getAmount()));
            balanceService.updateBalance(balanceOrigin);
            balanceDestination.setAmount_pesos(balanceOrigin.getAmount_pesos().add(transaction.getAmount()));
            balanceService.updateBalance(balanceDestination);
                return Mono.just(transactionRepository.save(transaction));

            }else {
                return Mono.error(new Exception("El saldo no es suficiente para realizar transferencia"));
            }
        }


        //falta return

    }

    private Mono<Transaction> Deposit(Transaction transaction) {
        Balance balance = balanceRepository.findById(Long.valueOf(transaction.getDestination_account())).orElseThrow(()-> new RuntimeException("Cuenta no se encuentra en Saldo"));
        if (transaction.getCurrency() == 1){//0 pesos, 1 dolares
            balance.setAmount_dollars(balance.getAmount_dollars().add(transaction.getAmount()));
            balanceService.updateBalance(balance);
            return Mono.just(transactionRepository.save(transaction));
        }else {
            balance.setAmount_pesos(balance.getAmount_pesos().add(transaction.getAmount()));
            balanceService.updateBalance(balance);
            return Mono.just(transactionRepository.save(transaction));
        }
    }

    private Mono<Transaction> Withdrawal(Transaction transaction) {

        Balance balance = balanceRepository.findById(Long.valueOf(transaction.getDestination_account())).orElseThrow(()-> new RuntimeException("Cuenta no se encuentra en Saldo"));
        BigDecimal amount;
        if(transaction.getCuenta().getAccount_tipe().equals("CC") && transaction.getCurrency() == 1){//Las cuentas corrientes tienen sobregiro
            amount = balance.getOverdraft_limit().add(balance.getAmount_dollars());

            if(amount.compareTo(transaction.getAmount())>=0) {
                balance.setAmount_dollars(balance.getAmount_dollars().subtract(transaction.getAmount()));
                balanceService.updateBalance(balance);
                return Mono.just(transactionRepository.save(transaction));
            }else {
                return Mono.error(new RuntimeException("Saldo insuficiente para retiro"));
            }
        } else if (transaction.getCuenta().getAccount_tipe().equals("CC") && transaction.getCurrency() == 0) {
            amount = balance.getOverdraft_limit().add(balance.getAmount_pesos());
            if(amount.compareTo(transaction.getAmount())>=0) {
                balance.setAmount_pesos(balance.getAmount_pesos().subtract(transaction.getAmount()));
                balanceService.updateBalance(balance);
                return Mono.just(transactionRepository.save(transaction));
            }else {
                return Mono.error(new RuntimeException("Saldo insuficiente para retiro"));
            }
        }


        if (transaction.getCurrency() == 1){//0 pesos, 1 dolares
            if(balance.getAmount_dollars().compareTo(transaction.getAmount())>=0) {
                balance.setAmount_dollars(balance.getAmount_dollars().subtract(transaction.getAmount()));
                balanceService.updateBalance(balance);
                return Mono.just(transactionRepository.save(transaction));
            }else {
                return Mono.error(new RuntimeException("Saldo insuficiente para retiro"));
            }
        }else {
            if(balance.getAmount_pesos().compareTo(transaction.getAmount())>=0) {
                balance.setAmount_pesos(balance.getAmount_pesos().subtract(transaction.getAmount()));
                balanceService.updateBalance(balance);
                return Mono.just(transactionRepository.save(transaction));
            }else {
                return Mono.error(new RuntimeException("Saldo insuficiente para retiro"));
            }
        }
    }

    private Mono<Transaction> BillPayment(Transaction transaction) {
        return Mono.just(transaction);
    }
}
