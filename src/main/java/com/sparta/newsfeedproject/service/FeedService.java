package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

    @Autowired
    private FeedRepository feedRepository;



}
