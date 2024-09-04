package com.sparta.newsfeedproject.controller;

import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {

  private final FollowService followService;

  @GetMapping("/follow")
  public List<Follow> getFollowers(@RequestParam Long userId, @RequestParam int page, @RequestParam int size) {
    return followService.findFollowersById(userId, page, size).getContent();
  }

  @DeleteMapping("/follow/delete")
  public ResponseEntity<String> deleteFollowing(@RequestParam Long followingId) {
    followService.deleteByFollowingId(followingId);
    return ResponseEntity.ok("언팔로우 완료");
  }

  @PostMapping("/follow/add")
  public void followUser(@RequestParam String follower, @RequestParam String following) {
      followService.followUser(follower, following);
  }

}
