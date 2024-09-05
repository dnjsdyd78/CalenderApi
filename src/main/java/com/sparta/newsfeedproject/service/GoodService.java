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
        User user = userRepository.findById(userTokenDto.getUserId()).orElseThrow(()->new IllegalArgumentException("User not found"));
        Feed feed = feedRepository.findById(feedId).orElseThrow(()->new IllegalArgumentException("Feed not found"));

        //좋아요가 중복일 경우 예외처리
        if(goodRepository.existsByUserAndFeed(user, feed)){
            throw new IllegalArgumentException("Post already liked");
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
        User user = userRepository.findById(userTokenDto.getUserId()).orElseThrow(()->new IllegalArgumentException("User not found"));
        Feed feed = feedRepository.findById(feedId).orElseThrow(()->new IllegalArgumentException("Feed not found"));

        Good good = goodRepository.findByUserAndFeed(user, feed).orElseThrow(()->new IllegalArgumentException("Good not found"));

        goodRepository.delete(good);
    }

}
