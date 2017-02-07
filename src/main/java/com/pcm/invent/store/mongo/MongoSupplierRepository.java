package com.pcm.invent.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pcm.invent.store.model.Supplier;

public interface MongoSupplierRepository extends MongoRepository<Supplier, String> {

}
