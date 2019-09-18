package com.sts.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by fangyi on 2019/9/17
 */
@EnableEurekaServer
@SpringBootApplication
public class StsEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StsEurekaApplication.class, args);
    }
}
