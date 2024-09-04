package com.sparta.newsfeedproject.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter


public class FeedRequestDto {
    private String title;
    private String content;
}