package com.sparta.newsfeedproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserTokenDto {

    private Long userId;

    private String email;

}
