package com.app.simplebalancewebflux.repositories;

import com.app.simplebalancewebflux.entities.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, String> {

    Flux<Transaction> findAllByItem(String item, Pageable pageable);
}
