package com.bt.au.shoppingcart.event;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

//@Component
public class EventProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);

    public static void main(String[] args) {

        // Configure properties
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create Producer
        try(KafkaProducer<String, Object> producer = new KafkaProducer<String, Object>(prop)) {

            // Create a producer record
            ProducerRecord<String, Object> record = new ProducerRecord<>("my_topic", "MY_KEY","Java Consumer");

            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (null == e) {
                        EventProducer.buildCallbackLogInfo(recordMetadata);
                    } else {
                        LOGGER.error("Error while prodicing", e);
                    }
                }
            });

            producer.flush();

        }catch (Exception e) {
            LOGGER.error("Error while releasing resource", e);
        }




    }

    public static void buildCallbackLogInfo(RecordMetadata recordMetadata) {
        LOGGER.info("Received new metadata. \n" +
                        "Topic: {} \n" +
                        "Partition: {} \n" +
                        "Offset: {} \n" +
                        "Timestamp: {} \n",

                new Object[] {
                        recordMetadata.topic(),
                        recordMetadata.partition(),
                        recordMetadata.offset(),
                        recordMetadata.timestamp()
                }
        );
    }

}
