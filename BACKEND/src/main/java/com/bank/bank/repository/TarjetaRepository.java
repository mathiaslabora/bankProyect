package com.bank.bank.repository;

import com.bank.bank.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Card, Long> {
}