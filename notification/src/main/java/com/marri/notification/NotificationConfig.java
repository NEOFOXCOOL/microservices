package com.marri.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.lang.String;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationConfig {

    NotificationRepository notificationRepository;

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.notification}")
    private String notificationQueue;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    @Bean
    public TopicExchange internalExchange(){
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue notificationQueue(){
        return new Queue(this.notificationQueue);
    }

    @Bean
    public Binding internalToNotificationBading(){
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalExchange())
                .with(this.internalNotificationRoutingKey);
    }
}
