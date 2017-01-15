package com.pcm.invent.store.boot;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableMongoRepositories(basePackageClasses = Application.class)
@EnableCaching
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "invent-store.mongo")
public class MongoConfig extends AbstractMongoConfiguration {

	@NotEmpty
	private String serverAddress;

	@NotEmpty
	private String userName;

	@NotEmpty
	private String password;

	@NotEmpty
	private String dbToAuthenticate;

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbToAuthenticate() {
		return dbToAuthenticate;
	}

	public void setDbToAuthenticate(String dbToAuthenticate) {
		this.dbToAuthenticate = dbToAuthenticate;
	}

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
		credentials.add(MongoCredential.createCredential(userName, dbToAuthenticate, password.toCharArray()));
		return new MongoClient(new ServerAddress(serverAddress), credentials, options);
	}

	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate template = new MongoTemplate(mongo(), getDatabaseName());
		template.setWriteConcern(MAJORITY);
		return template;

	}

}
