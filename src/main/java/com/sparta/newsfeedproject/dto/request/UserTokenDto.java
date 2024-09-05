package com.sparta.newsfeedproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UserTokenDto {

    private Long userId;

    private String email;

}
