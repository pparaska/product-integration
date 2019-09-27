package com.cg.dcp.ecom.productintegration.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MultipleMongoProperties.class)
public class MultipleMongoConfig {

	@Autowired
    private final MultipleMongoProperties mongoProperties;

    @Bean(name = "productMongoTemplate")
    public MongoTemplate productMongoTemplate() throws Exception {
        return new MongoTemplate(productFactory(this.mongoProperties.getProduct()));
    }

    @Bean(name = "productCategoryMongoTemplate")
    public MongoTemplate productCategoryMongoTemplate() throws Exception {
        return new MongoTemplate(productCategoryFactory(this.mongoProperties.getProductcategory()));
    }

    public MongoDbFactory productFactory(final MongoProperties mongo) throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
                mongo.getDatabase());
    }

    public MongoDbFactory productCategoryFactory(final MongoProperties mongo) throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
                mongo.getDatabase());
    }

}
