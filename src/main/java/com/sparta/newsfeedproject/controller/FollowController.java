package com.sparta.newsfeedproject.controller;


import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/follow")
    public List<Follow> findFollowers(@RequestParam int page, @RequestParam int size) {
      return followService.getFollowers(page, size).getContent();
    }



}
