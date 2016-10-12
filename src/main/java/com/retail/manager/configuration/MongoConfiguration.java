package com.retail.manager.configuration;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@EnableMongoRepositories(basePackages = "com.retail.manager.repository")
public class MongoConfiguration {

	@Bean
	public MongoDbFactory mongoDbFactory() throws UnknownHostException {
		SimpleMongoDbFactory factory = new SimpleMongoDbFactory(new MongoClient("localhost"), "test");
		factory.setWriteConcern(WriteConcern.MAJORITY);
		return factory;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		return new MongoTemplate(mongoDbFactory());
	}
}