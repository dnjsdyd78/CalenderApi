package com.sparta.newsfeedproject.controller;


import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {

    private FollowService followService;

    @GetMapping("/follow")
    public List<Follow> findFollowers(@RequestParam int page, @RequestParam int size) {
      return followService.getFollowers(page, size).getContent();
    }

    @PostMapping("/follow/add")
    public void followUser(@RequestParam String follower, @RequestParam String following) {
        followService.followUser(follower, following);
    }



}
