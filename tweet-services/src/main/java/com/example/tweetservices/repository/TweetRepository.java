package com.example.tweetservices.repository;

import com.example.tweetservices.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {}
