package com.sparta.newsfeedproject.controller;

import com.sparta.newsfeedproject.dto.response.CommonResponseDto;
import com.sparta.newsfeedproject.dto.response.FeedRequestDto;
import com.sparta.newsfeedproject.dto.response.FeedResponseDto;
import com.sparta.newsfeedproject.service.FeedService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

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
