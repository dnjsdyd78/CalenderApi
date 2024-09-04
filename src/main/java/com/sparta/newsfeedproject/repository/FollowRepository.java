package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
  List<Follow> findByFollower(User follower);
  List<Follow> findByFollowing(User following);
  Page<Follow> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
