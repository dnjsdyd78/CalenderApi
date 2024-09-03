package com.sparta.newsfeedproject.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
// null값은 JSON응답에서 제외
@JsonInclude(JsonInclude.Include.NON_NULL)

public class FeedResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long likeCount;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
