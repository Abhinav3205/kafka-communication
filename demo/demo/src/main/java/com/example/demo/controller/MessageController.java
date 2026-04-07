package com.example.demo.controller;

import com.example.demo.service.KafkaProducerService;
import com.example.demo.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaProducerService producerService;
    private final RedisService redisService;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        producerService.sendMessage(message);
        redisService.saveMessage(message);
        return "Message sent to Kafka!";
    }
}