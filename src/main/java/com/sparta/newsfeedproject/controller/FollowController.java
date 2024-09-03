package com.sparta.newsfeedproject.controller;


import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/follow")
    public List<Follow> findFollowers(@RequestParam Long userId, @RequestParam int page, @RequestParam int size) {
      return followService.getFollowers(userId, page, size).getContent();
    }




}
