package com.example.demo.service;

import com.example.demo.config.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public void sendMessage(String message) {
        kafkaTemplate.send(kafkaProperties.getTopic(), message);
        System.out.println("Topic being used: " + kafkaProperties.getTopic());

        System.out.println("Message sent: " + message);
    }
}