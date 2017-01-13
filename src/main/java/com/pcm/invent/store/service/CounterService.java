package com.pcm.invent.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.pcm.invent.store.model.Counters;

@Service
public class CounterService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public int getNextSquence(String name) {

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(name));
		Update update = new BasicUpdate("{ $inc: { seq: 1 } }");

		Counters counter = mongoTemplate.findAndModify(query, update, Counters.class);
		return counter.getSeq();
	}

}
