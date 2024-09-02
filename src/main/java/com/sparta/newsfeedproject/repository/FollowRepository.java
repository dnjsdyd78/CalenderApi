package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
