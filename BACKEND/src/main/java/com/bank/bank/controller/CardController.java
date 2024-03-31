package com.bank.bank.controller;

import com.bank.bank.model.Card;
import com.bank.bank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/getAll")
    public Flux<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Mono<Card> getCardById(@PathVariable int id) {
        return cardService.getCardById(id);
    }

    @PostMapping("/newCard")
    public Mono<Card> createCard(@RequestBody Card card) {
        return cardService.newCard(card);
    }

    @PutMapping("/updateCard")
    public Mono<Card> updateCard(@RequestBody Card card) {
        return cardService.updateCard(card);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> deleteCard(@PathVariable int id) {
        return cardService.deleteCard(id);
    }
}
