package com.microservices_app.notification_service.Services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @KafkaListener(topics = "user_registered", groupId = "notify-group")
    public void consume(String email) {
        System.out.println("📩 Sending welcome email to: " + email);
    }
}
