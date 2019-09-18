package com.sts.futures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by fangyi on 2019/9/17
 */
@EnableEurekaClient
@SpringBootApplication
public class FuturesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuturesApplication.class, args);
    }
}
