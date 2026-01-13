package com.app.simplebalancewebflux.services;

import com.app.simplebalancewebflux.entities.Transaction;
import com.app.simplebalancewebflux.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Flux<Transaction> getAllByItem(String search){
        return transactionRepository.findAllByItem(search, PageRequest.of(1,100));
    }

    public Flux<Transaction> getAll(){
        return transactionRepository.findAll();
    }

    public Mono<Transaction> saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
