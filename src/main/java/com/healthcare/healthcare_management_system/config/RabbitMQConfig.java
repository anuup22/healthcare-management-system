package com.healthcare.healthcare_management_system.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final String NOTIFICATION_QUEUE = "notificationQueue";
  public static final String NOTIFICATION_EXCHANGE = "notificationExchange";
  public static final String ROUTING_KEY = "notificationRoutingKey";

  @Bean
  public Queue notificationQueue() {
    return new Queue(NOTIFICATION_QUEUE, true);
  }

  @Bean
  public TopicExchange notificationExchange() {
    return new TopicExchange(NOTIFICATION_EXCHANGE);
  }

  @Bean
  public Binding notificationBinding(Queue notificationQueue, TopicExchange notificationExchange) {
    return BindingBuilder.bind(notificationQueue).to(notificationExchange).with(ROUTING_KEY);
  }
}
