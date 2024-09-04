package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
//  Page<Follow> findAllByFollowerIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

  // 팔로잉ID로 검색해서 삭제
  void deleteByFollowing_IdAndStandardId(Long standardId, Long followingId);


   //특정 사용자가 팔로우한 사람들의 목록을 가져옴
    List<Follow> findAllByFollowingId(User user);

//    List<Follow> findByFollower(User follower);

    List<Follow> findByFollowing(User following);

    Page<Follow> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
