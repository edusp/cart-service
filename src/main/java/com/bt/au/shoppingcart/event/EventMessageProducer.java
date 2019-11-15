package com.bt.au.shoppingcart.event;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Service
@ConditionalOnExpression(value = "${enable-event:false}")
public class EventMessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventMessageProducer.class);

    @Value("#{'${kafka.event.topic}'.split(',')}")
    private List<String> topic;

    private KafkaTemplate<String, String> kafkaTemplateProducer;

    @Autowired
    public EventMessageProducer(KafkaTemplate<String, String> kafkaTemplateProducer) {
        this.kafkaTemplateProducer = kafkaTemplateProducer;
    }

    public void send(String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplateProducer.send(this.topic.get(0), msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOGGER.error("Failed to Producer", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                EventMessageProducer.buildCallbackLogInfo(result.getRecordMetadata());
            }
        });

    }

    private static void buildCallbackLogInfo(RecordMetadata recordMetadata) {
        LOGGER.info("Received new metadata. \n" +
                        "Topic: {} \n" +
                        "Partition: {} \n" +
                        "Offset: {} \n" +
                        "Timestamp: {} \n",

                        recordMetadata.topic(),
                        recordMetadata.partition(),
                        recordMetadata.offset(),
                        recordMetadata.timestamp()
        );
    }

}
