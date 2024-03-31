package com.bank.bank.service;

import com.bank.bank.model.Card;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardService {
    Flux<Card> getAllCards();

    Mono<Card> getCardById(int cardId);

    Mono<Card> newCard(Card card);

    Mono<Card> updateCard(Card card);

    Mono<Boolean> deleteCard(int cardId);
}
