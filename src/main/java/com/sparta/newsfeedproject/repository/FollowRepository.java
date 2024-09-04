package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
  Page<Follow> findAllByFollower_IdOrderByCreatedAtDesc(Long userId, Pageable pageable);

  // 팔로잉ID로 검색해서 삭제
  void deleteByFollowing_Id(Long followingId);


}
