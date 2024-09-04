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



  List<Follow> findStandardIdByFollowingId(User user);

//    List<Follow> findByFollower(User follower);

//  List<Follow> findByFollowing(User following);

}
