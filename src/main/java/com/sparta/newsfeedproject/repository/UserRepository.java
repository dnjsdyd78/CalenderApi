package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    Optional<User> findById(Long id);//ID로 사용자 조회

}