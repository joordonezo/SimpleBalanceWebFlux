package com.app.simplebalancewebflux.controllers;

import com.app.simplebalancewebflux.entities.Transaction;
import com.app.simplebalancewebflux.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/")
    public String home() {
        return "Application Running!!!";
    }

    @GetMapping("/list/{q}")
    public Flux<Transaction> getList(@PathVariable String q){
        return transactionService.getAllByItem(q);
    }

    @PostMapping("/transaction")
    public Mono<Transaction> saveTransaction(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);
    }
}
