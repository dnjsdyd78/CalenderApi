package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.Feed;
import com.sparta.newsfeedproject.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    //특정 사용자(userId)에 대해 페이지네이션된 피드를 조회
    Page<Feed> findByUserId(User userId, PageRequest pageRequest);
    //여러 사용자의 피드를 페이징해서 들고옴
    Page<Feed> findByUserIdIn(List<User> users, PageRequest pageRequest);
}
