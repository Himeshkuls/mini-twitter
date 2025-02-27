package com.example.tweetservices.service;

import com.example.tweetservices.model.Tweet;
import com.example.tweetservices.repository.TweetRepository;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class TweetService {
    private final TweetRepository tweetRepository;
    private final SqsClient sqsClient;

    public TweetService(TweetRepository tweetRepository, SqsClient sqsClient) {
        this.tweetRepository = tweetRepository;
        this.sqsClient = sqsClient;
    }

    public Tweet postTweet(Long userId, String content) {
        Tweet tweet = new Tweet(userId, content);
        tweet = tweetRepository.save(tweet);
        sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl("https://sqs.us-east-2.amazonaws.com/869935078800/tweet-queue")
                .messageBody("{\"tweetId\": " + tweet.getId() + ", \"userId\": " + userId + "}")
                .build());
        return tweet;
    }
}
