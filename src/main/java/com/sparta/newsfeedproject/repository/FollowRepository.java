package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

  // 팔로잉ID로 검색해서 삭제
  void deleteByStandardIdAndFollowingId(User standardId, User followingId);
  // 팔로워 목록 조회
  Page<Follow> findByFollowingId(User followingId, Pageable pageable);


   //특정 사용자가 팔로우한 사람들의 목록을 가져옴
    List<Follow> findAllByFollowingId(User followingId);

    List<Follow> findStandardIdByFollowingId(User user);

    //팔로워와 팔로잉 관계를 조회
    Follow findByFollowingAndFollower(User follower, User following);


}
