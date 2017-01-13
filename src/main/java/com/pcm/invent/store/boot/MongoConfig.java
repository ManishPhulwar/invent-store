package com.pcm.invent.store.boot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import static com.mongodb.WriteConcern.MAJORITY;
import com.pcm.invent.store.Application;

@Component
@Configuration
@EnableMongoRepositories(basePackageClasses= Application.class)
@EnableCaching
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "inventstore";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		MongoClientOptions.Builder builder = MongoClientOptions.builder();
		MongoClientOptions options = builder.connectionsPerHost(100).build();
		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		credentials.add(MongoCredential.createCredential("superuser", "admin","6789".toCharArray()));
		return new MongoClient(new ServerAddress("localhost"),credentials,options);
	}
	
	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception{
		MongoTemplate template = new MongoTemplate(mongo(), getDatabaseName());
		template.setWriteConcern(MAJORITY);
		return template;
		
	}

}
