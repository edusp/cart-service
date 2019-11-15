package com.bt.au.shoppingcart.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnExpression(value = "${enable-event:false}")
public class EventMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventMessageConsumer.class);


    @KafkaListener(topics = "product_topic")
    public void consumer(@Payload String message) {
        LOGGER.info(String.format("Consumed message -> %s", message));

    }



}
