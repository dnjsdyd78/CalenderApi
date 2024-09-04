package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

  private final FollowRepository followRepository;

  // 유저ID로 팔로워들 조회
  public Page<Follow> findFollowersById(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return followRepository.findAllByFollower_IdOrderByCreatedAtDesc(userId, pageable);
  }

  // 유저ID로 팔로잉 단일 삭제
  @Transactional
  public void deleteByFollowingId(Long followingId) {
    followRepository.deleteByFollowing_Id(followingId);
  }
}

