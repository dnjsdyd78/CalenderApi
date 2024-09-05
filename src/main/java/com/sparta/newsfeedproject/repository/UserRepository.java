package com.sparta.newsfeedproject.repository;

import com.sparta.newsfeedproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    User findByUserName(String username);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.deletedAt IS NOT NULL")
    Optional<User> findDeletedUserById(@Param("id") Long id);

}