package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SqsClient sqsClient;

    public UserService(UserRepository userRepository, SqsClient sqsClient) {
        this.userRepository = userRepository;
        this.sqsClient = sqsClient;
    }

    public User registerUser(String username, String email) {
        User user = new User(username, email);
        user = userRepository.save(user);
        sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl("https://sqs.us-east-2.amazonaws.com/869935078800/tweet-queue")
                .messageBody("{\"userId\": " + user.getId() + ", \"username\": \"" + username + "\"}")
                .build());
        return user;
    }
}
