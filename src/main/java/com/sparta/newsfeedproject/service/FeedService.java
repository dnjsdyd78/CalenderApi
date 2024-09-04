package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.Feed;
import com.sparta.newsfeedproject.dto.response.FeedRequestDto;
import com.sparta.newsfeedproject.dto.response.FeedResponseDto;
import com.sparta.newsfeedproject.repository.FeedRepository;
import com.sparta.newsfeedproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService {


    private final FeedRepository feedRepository;

    @Transactional
    public FeedResponseDto updateFeed(Long id, FeedRequestDto requestDto) {
        Feed findFeed = feedRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        //JWT로 받아온 로그인계정과 id 일치시 로직실행
        /*if (findFeed.getUser.getId == loginId){}..... */

        Feed savedFeed = findFeed.update(requestDto);


        return new FeedResponseDto(savedFeed);
    }

    public void deleteFeed(Long id) {
        Feed findFeed = feedRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        //JWT로 받아온 로그인계정과 id 일치시 로직실행
        /*if (findFeed.getUser.getId == loginId){}..... */
        feedRepository.delete(findFeed);

    }

}
