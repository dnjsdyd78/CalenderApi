package com.sparta.newsfeedproject.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.newsfeedproject.domain.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class FeedResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long likeCount;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public FeedResponseDto(Feed feed) {
        this.id = feed.getId();
        this.title = feed.getTitle();
        this.content = feed.getContent();
        this.updateAt = feed.getUpdatedAt();
    }
}
