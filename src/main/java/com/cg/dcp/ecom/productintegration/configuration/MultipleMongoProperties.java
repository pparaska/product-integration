package com.cg.dcp.ecom.productintegration.configuration;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoProperties {

    private MongoProperties product = new MongoProperties();
    private MongoProperties productcategory = new MongoProperties();
}
