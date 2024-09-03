package com.sparta.newsfeedproject.controller;

import com.sparta.newsfeedproject.dto.request.FeedSaveRequestDto;
import com.sparta.newsfeedproject.dto.response.FeedDetailResponseDto;
import com.sparta.newsfeedproject.dto.response.FeedSaveResponseDto;
import com.sparta.newsfeedproject.dto.response.FeedSimpleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FeedService;


@RestController
@RequiredArgsConstructor
public class FeedController {
    private FeedService feedService;

    //feed 작성
    @PostMapping("api/feed/save")
    public ResponseEntity<FeedSaveResponseDto> saveFeed(@RequestBody FeedSaveRequestDto feedSaveRequestDto) {
        return ResponseEntity.ok(feedService.saveFeed(feedSaveRequestDto));
    }

    //feed 목록 조회
    @GetMapping("api/feed/{userId}")
    public ResponseEntity<Page<FeedSimpleResponseDto>> getFeeds(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page) {
        Page<FeedSimpleResponseDto> feeds = feedService.getFeeds(userId, page);
        return ResponseEntity.ok(feeds);
    }

    //개별 피드 조회
    @GetMapping("/api/feed/detail/{feedId}")
    public ResponseEntity<FeedDetailResponseDto> getFeedDetail(@PathVariable Long feedId) {
        FeedDetailResponseDto feed = feedService.getFeedDetail(feedId);
        return ResponseEntity.ok(feed);
    }


}
