package com.sts.stock.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fangyi on 2019/9/19
 */
@Configuration
public class StockConfiguration {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    /**
     * 去除插入时生成的_class字段
     * @param mongoDbFactory mongoDbFactory
     * @return
     */
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MappingMongoConverter converter) {
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new MongoTemplate(mongoDbFactory, converter);
    }
}
