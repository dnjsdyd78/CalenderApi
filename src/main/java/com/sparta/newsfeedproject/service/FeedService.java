package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;



}
