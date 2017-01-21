package com.pcm.invent.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pcm.invent.store.model.Inventory;

public interface MongoInventoryRepository extends MongoRepository<Inventory, String> {

	Inventory findByName(String inventoryName);
}
