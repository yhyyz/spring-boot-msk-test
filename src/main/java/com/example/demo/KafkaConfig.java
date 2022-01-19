package com.example.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * @author shuang.kou
 */
@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.topic}")
    String topic;

    @Value("${kafka.topic.partition}")
    int partition;

    @Value("${kafka.topic.replicas}")
    int replicas;


    @Bean
    public RecordMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public NewTopic Topic() {
        return new NewTopic(topic, partition, (short) replicas);
    }

}
