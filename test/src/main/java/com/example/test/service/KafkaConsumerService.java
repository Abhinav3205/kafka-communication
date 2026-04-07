package com.example.test.service;


import com.example.test.config.KafkaProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final StringRedisTemplate redisTemplate;
    private final KafkaProperties kafkaProperties;

    @PostConstruct
    public void init() {
        System.out.println("Listening to topic: " + kafkaProperties.getTopic());
    }

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "my-group")
    public void consume(String message) {

        System.out.println("🔥 Received message: " + message);

        // Store in Redis
        redisTemplate.opsForValue().set("lastMessage", message);

        System.out.println("✅ Stored in Redis");
    }
}