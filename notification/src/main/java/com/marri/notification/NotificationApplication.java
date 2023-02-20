package com.marri.notification;


import com.marri.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(
        scanBasePackages = {
                "com.marri.notification",
                "com.marri.amqp",
        }
)
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class,args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer producer,
//            NotificationConfig notificationConfig
//            ){
//          return args -> {
//              producer.publish(
//                      new Person("Zakariae","Marri"),
//                      notificationConfig.getInternalExchange(),
//                      notificationConfig.getInternalNotificationRoutingkKey()
//              );
//          };
//            }
//
//            record  Person(String firstName,String lastName){}
}
