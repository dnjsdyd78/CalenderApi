package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.Feed;
import com.sparta.newsfeedproject.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    Page<Feed> findAllByUser(User user, PageRequest pageRequest);
}
