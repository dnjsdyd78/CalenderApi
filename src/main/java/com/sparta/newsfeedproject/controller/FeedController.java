package com.sparta.newsfeedproject.controller;

import com.sparta.newsfeedproject.dto.request.FeedSaveRequestDto;
import com.sparta.newsfeedproject.dto.response.FeedSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.FeedService;

@RestController
@RequiredArgsConstructor
public class FeedController {
    private FeedService feedService;

    //feed작성
    @PostMapping("api/feed/save")
    public ResponseEntity<FeedSaveResponseDto> saveFeed(@RequestBody FeedSaveRequestDto feedSaveRequestDto) {
        return ResponseEntity.ok(feedService.saveFeed(feedSaveRequestDto));
    }





    //feed 조회 기본정렬이 생성일자 기준 내림차순,
    // 10개씩 페이지네이션하여, 각 페이지 당 뉴스피드 데이터가 10개씩 나오게 합니다.
    //다른 사람의 뉴스피드는 볼 수 없습니다.


}
