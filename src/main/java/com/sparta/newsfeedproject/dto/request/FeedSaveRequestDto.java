package com.sparta.newsfeedproject.dto.request;

import lombok.Getter;

@Getter
public class FeedSaveRequestDto {

    private String title;
    private String content;
    private Long userId;

}
