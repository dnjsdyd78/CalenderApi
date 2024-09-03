package com.sparta.newsfeedproject.controller;

import com.sparta.newsfeedproject.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;



}
