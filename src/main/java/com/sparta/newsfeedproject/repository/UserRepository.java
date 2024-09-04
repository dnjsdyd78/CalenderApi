package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    User findByUserName(String username);

    boolean existsByIdAndDeletedAtIsNotNull(Long userId);

}