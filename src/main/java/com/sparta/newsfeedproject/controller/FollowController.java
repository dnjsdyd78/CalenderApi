package com.sparta.newsfeedproject.controller;

import com.sparta.newsfeedproject.annotation.Auth;
import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.dto.request.UserDto;
import com.sparta.newsfeedproject.dto.request.UserTokenDto;
import com.sparta.newsfeedproject.dto.request.UserDto;
import com.sparta.newsfeedproject.dto.request.UserTokenDto;
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
  public List<Follow> getFollowers(@Auth UserTokenDto userTokenDto, @RequestParam int page, @RequestParam int size) {
    return followService.findFollowers(userTokenDto, page, size).getContent();
  }

  @DeleteMapping("/follow/delete")
  public ResponseEntity<String> deleteFollowing(@Auth UserTokenDto userTokenDto, @RequestParam Long followingId) {
    followService.unfollow(userTokenDto, followingId);
    return ResponseEntity.ok("언팔로우 완료");
  }

  @PostMapping("/follow/add")
  public void followUser(@Auth UserTokenDto userTokenDto, @RequestParam Long followUserId) {
      followService.followUser(userTokenDto, followUserId);
  }

}
