package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;


    public Page<Follow> getFollowers(int page, int size) {
      Pageable pageable = PageRequest.of(page, size);
      return followRepository.findAllByOrderByCreatedAtDesc(pageable);
  }
}
