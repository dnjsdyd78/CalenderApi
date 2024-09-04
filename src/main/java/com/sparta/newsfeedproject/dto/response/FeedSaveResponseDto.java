package com.sparta.newsfeedproject.dto.response;

import lombok.Getter;

@Getter
public class FeedSaveResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final Long likeCount;

    public FeedSaveResponseDto(Long id, String title, String content, Long likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
    }
}
