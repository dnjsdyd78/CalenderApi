package com.sparta.newsfeedproject.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
// null값은 JSON요청에서 제외
@JsonInclude(JsonInclude.Include.NON_NULL)

public class FeedRequestDto {
    private Long id;
    private String title;
    private String content;
    private Long likeCount;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}