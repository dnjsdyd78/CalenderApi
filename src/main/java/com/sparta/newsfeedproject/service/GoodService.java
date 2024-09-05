package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.Feed;
import com.sparta.newsfeedproject.domain.Good;
import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.dto.request.UserTokenDto;
import com.sparta.newsfeedproject.repository.FeedRepository;
import com.sparta.newsfeedproject.repository.GoodRepository;
import com.sparta.newsfeedproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private GoodRepository goodRepository;

    @Transactional
    public void goodfeed(UserTokenDto userTokenDto, Long feedId){
        //사용자, 게시글 조회
        User user = userRepository.findById(userTokenDto.getUserId()).orElseThrow(()->new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Feed feed = feedRepository.findById(feedId).orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        //좋아요가 중복일 경우 예외처리
        if(goodRepository.existsByUserAndFeed(user, feed)){
            throw new IllegalArgumentException("이미 좋아요를 누른 게시글입니다.");
        }

        //좋아요 기능
        Good good = new Good();
        good.setUser(user);
        good.setFeed(feed);

        goodRepository.save(good);
    }

    //좋아요 삭제
    @Transactional
    public void ungoodfeed(UserTokenDto userTokenDto, Long feedId){
        User user = userRepository.findById(userTokenDto.getUserId()).orElseThrow(()->new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Feed feed = feedRepository.findById(feedId).orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        Good good = goodRepository.findByUserAndFeed(user, feed).orElseThrow(()->new IllegalArgumentException("좋아요 한 게시글이 아닙니다."));

        goodRepository.delete(good);
    }

}
