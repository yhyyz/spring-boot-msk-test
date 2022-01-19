package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class MessageProducer {


    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public MessageProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Object o) {

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, o);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> sendResult) {
                logger.info("Success发送消息到" + topic + "-> " + sendResult.getProducerRecord().value().toString());
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Fail发送消息：{} 失败，原因：{}", o.toString(), throwable.getMessage());
            }
        });
    }
}
