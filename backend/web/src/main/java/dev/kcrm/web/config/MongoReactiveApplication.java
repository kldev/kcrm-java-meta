package dev.kcrm.web.config;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import dev.kcrm.web.data.repositories.ClientCrudRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
//
//@Configuration
//@EnableReactiveMongoRepositories(basePackageClasses = ClientCrudRepository.class)
//public class MongoReactiveApplication extends AbstractReactiveMongoConfiguration {
//
//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create();
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return "crm";
//    }
//
//    @Override
//    public MongoClient reactiveMongoClient() {
//        return MongoClients.create();
//    }
//
//    @Bean
//    public ReactiveMongoTemplate reactiveMongoTemplate() {
//        return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
//    }
//}
