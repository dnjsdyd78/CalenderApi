package com.sparta.newsfeedproject.dto.request;

import com.sparta.newsfeedproject.domain.Feed;
import lombok.Getter;

@Getter


public class FeedRequestDto{
    private String title;
    private String content;

    public Feed toEntity(){
        return Feed.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}