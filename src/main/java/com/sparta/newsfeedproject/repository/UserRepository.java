package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
