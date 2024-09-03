package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;


    public Page<Follow> getFollowers(Long userId, int page, int size) {

      Pageable pageable = PageRequest.of(page, size);
      return followRepository.findAllByFollowing_IdOrderByCreatedAtDesc(userId, pageable);
    }

}
