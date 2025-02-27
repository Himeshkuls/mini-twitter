package com.example.tweetservices.controller;

import com.example.tweetservices.model.Tweet;
import com.example.tweetservices.service.TweetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping("/post")
    public Tweet post(@RequestParam Long userId, @RequestParam String content) {
        return tweetService.postTweet(userId, content);
    }
}
