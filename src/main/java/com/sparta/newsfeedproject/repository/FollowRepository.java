package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
   //특정 사용자가 팔로우한 사람들의 목록을 가져옴
    List<Follow> findAllByUser(User user);
}
