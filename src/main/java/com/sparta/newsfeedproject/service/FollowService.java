package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.dto.request.UserTokenDto;
import com.sparta.newsfeedproject.repository.FollowRepository;
import com.sparta.newsfeedproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

  private final FollowRepository followRepository;
  private final UserRepository userRepository;

  // 나를 팔로우하는 사람들 조회 (페이징 포함)
  public Page<Follow> findFollowers(UserTokenDto userTokenDto, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    User user = userRepository.findById(userTokenDto.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));
    return followRepository.findByFollowingId(user, pageable);
  }

  // 유저ID로 팔로잉 단일 삭제
  @Transactional
  public void unfollow(UserTokenDto userTokenDto, Long followingId) {
    User standardUser = userRepository.findById(userTokenDto.getUserId()).orElseThrow(
        ()->new RuntimeException("유저가 존재하지 않습니다."));
    User followingUser = userRepository.findById(followingId).orElseThrow(
        ()->new RuntimeException("팔로잉유저가 존재하지 않습니다."));
    followRepository.deleteByStandardIdAndFollowingId(standardUser, followingUser);
  }

//
//  @Transactional
//  public void followUser(String followerUsername, String followingUsername) {
//    User follower = userRepository.findByUserName(followerUsername);
//    User following = userRepository.findByUserName(followingUsername);
//  }

//  public Page<Follow> getFollowers(int page, int size) {
//    Pageable pageable = PageRequest.of(page, size);
//    return followRepository.findByUserId(pageable);
//  }

}

