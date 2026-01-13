package com.app.simplebalancewebflux.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "transaction")
public class Transaction {
    @Id
    private String id;
    private String item;
    private Double value;
}
