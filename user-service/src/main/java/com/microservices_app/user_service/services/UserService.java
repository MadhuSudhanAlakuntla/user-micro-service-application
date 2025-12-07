package com.microservices_app.user_service.services;

import com.microservices_app.user_service.Entity.User;
import com.microservices_app.user_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository repo;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public User saveUser(User user) {
        User saved = repo.save(user);

        // Send Kafka event
        kafkaTemplate.send("user_registered", saved.getEmail());

        return saved;
    }
}
