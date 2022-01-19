package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Value("${kafka.topic.topic}")
    String topic;
    private final MessageProducer producer;
    private AtomicLong atomicLong = new AtomicLong();

    MessageController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public void sendMessageToKafkaTopic(@RequestParam("name") String name) {
        this.producer.sendMessage(topic, new Message(atomicLong.addAndGet(1), name));
    }
}
