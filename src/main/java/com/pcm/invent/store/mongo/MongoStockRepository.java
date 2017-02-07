package com.pcm.invent.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pcm.invent.store.model.Stock;

public interface MongoStockRepository extends MongoRepository<Stock, String> {

}
