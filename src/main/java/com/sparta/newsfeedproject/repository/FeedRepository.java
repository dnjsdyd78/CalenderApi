package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {

}