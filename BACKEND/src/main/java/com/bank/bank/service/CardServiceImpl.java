package com.bank.bank.service;

import com.bank.bank.model.Card;
import com.bank.bank.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService{
    @Autowired
    CardRepository cardRepository;
    @Override
    public Flux<Card> getAllCards() {
        return Flux.fromIterable(cardRepository.findAll());
    }

    @Override
    public Mono<Card> getCardById(int cardId) {
        return Mono.justOrEmpty(cardRepository.findById(Long.valueOf(cardId)));
    }

    @Override
    public Mono<Card> newCard(Card card) {
        return Mono.just(cardRepository.save(card));
    }

    @Override
    public Mono<Card> updateCard(Card card) {
        return Mono.just(card.getCard_id())
                .flatMap(cardId -> {
                    Optional<Card> optionalCard = cardRepository.findById(Long.valueOf(cardId));
                    if (optionalCard.isPresent()) {
                        Card existingCard = optionalCard.get();
                        existingCard.setCard_number(card.getCard_number());
                        existingCard.setAccount_number(card.getAccount_number());
                        existingCard.setIssue_date(card.getIssue_date());
                        existingCard.setExpiration_date(card.getExpiration_date());
                        existingCard.setCard_type(card.getCard_type());
                        existingCard.setLimit_amount(card.getLimit_amount());
                        existingCard.setAvailable_balance(card.getAvailable_balance());
                        existingCard.setClosing_date(card.getClosing_date());
                        return Mono.just(cardRepository.save(existingCard));
                    } else {
                        return Mono.empty();
                    }
                });

    }

    @Override
    public Mono<Boolean> deleteCard(int cardId) {
        return Mono.fromCallable(() -> {
            cardRepository.deleteById(Long.valueOf(cardId));
            return true;
        });
    }
}
