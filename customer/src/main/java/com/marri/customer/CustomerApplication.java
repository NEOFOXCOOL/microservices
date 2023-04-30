package com.marri.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.marri.amqp",
                "com.marri.customer",
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.marri.client"
)
@PropertySources(
        @PropertySource(
                "classpath:client-${spring.profiles.active}.properties"
        )
)
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
