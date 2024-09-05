package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.annotation.Auth;
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
  // 유저ID로 팔로워들 조회
  public Page<Follow> findFollowersById(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return followRepository.findAllByFollower_IdOrderByCreatedAtDesc(userId, pageable);
  }

  //팔로우 기능 구현
  @Transactional
  public void followUser(UserTokenDto userTokenDto, Long followUserId) {
    //userId와 followUserId를 사용하여 User엔티티를 조회한다 / 존재하지 않을시 예외처리
    User user = userRepository.findById(userTokenDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    User followUser = userRepository.findById(followUserId).orElseThrow(() -> new IllegalArgumentException("팔로우 할 대상을 찾을 수 없습니다"));

    //이미 팔로우 관계가 존재하는지 확인
    Follow existingFollow = followRepository.findByFollowingAndFollower(followUser, user);
    if(existingFollow != null) {
      throw new IllegalStateException("이미 팔로우 한 대상입니다.");
    }

    //새로운 팔로우 관계 저장
    Follow follow = Follow.builder().follower(user).following(followUser).build();
    followRepository.save(follow);//데이터베이스에 저장
  }

  public Page<Follow> getFollowers(int page, int size) {
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
}

