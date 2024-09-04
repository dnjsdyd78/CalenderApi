package com.sparta.newsfeedproject.controller;

import com.sparta.newsfeedproject.dto.response.CommonResponseDto;
import com.sparta.newsfeedproject.dto.response.FeedRequestDto;
import com.sparta.newsfeedproject.dto.response.FeedResponseDto;
import com.sparta.newsfeedproject.service.FeedService;
import lombok.Getter;
import com.sparta.newsfeedproject.dto.request.FeedSaveRequestDto;
import com.sparta.newsfeedproject.dto.response.FeedDetailResponseDto;
import com.sparta.newsfeedproject.dto.response.FeedSaveResponseDto;
import com.sparta.newsfeedproject.dto.response.FeedSimpleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;
    //feed 작성
    @PostMapping("api/feed/save")
    public ResponseEntity<FeedSaveResponseDto> saveFeed(@RequestBody FeedSaveRequestDto feedSaveRequestDto) {
        return ResponseEntity.ok(feedService.saveFeed(feedSaveRequestDto));
    }

    //특정 유저의 feed 목록 조회
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

    //팔로우한 사람들의 뉴스피드 조회
    @GetMapping("api/feed/followed/{userId}")
    public ResponseEntity<Page<FeedSimpleResponseDto>> getFollowFeeds(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0")int page){
        Page<FeedSimpleResponseDto> feeds = feedService.getFollowFeeds(userId, page);
        return ResponseEntity.ok(feeds);
    }


    @PatchMapping("/feed/{id}")
    public ResponseEntity<CommonResponseDto> updateFeed(@PathVariable Long id, @RequestBody FeedRequestDto requestDto){
        FeedResponseDto responseDto = feedService.updateFeed(id, requestDto);

        return new ResponseEntity<>(new CommonResponseDto<>(200, "success", responseDto), HttpStatus.OK);
    }

    @DeleteMapping("/feed/{id}")
    public ResponseEntity<CommonResponseDto> deleteFeed(@PathVariable Long id){
        feedService.deleteFeed(id);

        return new ResponseEntity<>(new CommonResponseDto<>(200, "success", null), HttpStatus.OK);

    }

}
