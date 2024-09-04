package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.domain.User;
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

/*  // 유저ID로 팔로워들 조회
  public Page<Follow> findFollowersById(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return followRepository.findAllByFollowerIdOrderByCreatedAtDesc(userId, pageable);
  }*/
//
//  @Transactional
//  public void followUser(String followerUsername, String followingUsername) {
//    User follower = userRepository.findByUserName(followerUsername);
//    User following = userRepository.findByUserName(followingUsername);
//  }

  public Page<Follow> getFollowers(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return followRepository.findAllByOrderByCreatedAtDesc(pageable);
  }

  // 유저ID로 팔로잉 단일 삭제
  @Transactional
  public void deleteByFollowingId(User user, Long followingId) {
    followRepository.deleteByFollowing_IdAndStandardId(user.getId(), followingId);
  }
}

