package com.pcm.invent.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pcm.invent.store.model.Category;

public interface MongoCategoryRepository extends MongoRepository<Category, String> {

	Category findByName(String categoryName);
	Category findById(int id);

}
