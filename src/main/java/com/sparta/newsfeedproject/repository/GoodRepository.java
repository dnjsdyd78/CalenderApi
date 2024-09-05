package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.Feed;
import com.sparta.newsfeedproject.domain.Good;
import com.sparta.newsfeedproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {
    boolean existsByUserAndFeed(User user, Feed feed);
    Optional<Good> findByUserAndFeed(User user, Feed feed);
}
