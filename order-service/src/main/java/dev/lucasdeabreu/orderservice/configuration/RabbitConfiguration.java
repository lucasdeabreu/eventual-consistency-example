package dev.lucasdeabreu.orderservice.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue orderQueue(@Value("${queue.name.order}") String orderQueue) {
        return new Queue(orderQueue, true);
    }
}
