package com.pcm.invent.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pcm.invent.store.model.ItemSupplierMap;

public interface MongoItemSupplierMapRepository extends MongoRepository<ItemSupplierMap, String> {

}
