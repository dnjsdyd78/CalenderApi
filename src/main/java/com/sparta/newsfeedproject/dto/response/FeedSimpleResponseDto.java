package com.sparta.newsfeedproject.dto.response;

import lombok.Getter;

//각 피드 항목에 대한 정보를 담아서 여러개의 피드를 클라이언트에 보내줄 때 사용
@Getter
public class FeedSimpleResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final Long likeCount;

    public FeedSimpleResponseDto(Long id, String title, String content, Long likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
    }
}
