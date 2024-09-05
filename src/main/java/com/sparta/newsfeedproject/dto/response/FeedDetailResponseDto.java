package com.sparta.newsfeedproject.dto.response;

import lombok.Getter;

@Getter
public class FeedDetailResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String userName;  // userName 필드 사용
    private final Long likeCount;
    private final String createdAt;
    private final String updatedAt;

    public FeedDetailResponseDto(Long id, String title, String content, String userName, Long likeCount, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
