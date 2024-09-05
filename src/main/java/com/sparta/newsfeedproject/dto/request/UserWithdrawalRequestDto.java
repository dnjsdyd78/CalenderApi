package com.sparta.newsfeedproject.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithdrawalRequestDto {

    @NotNull(message = "password 는 필수파라미터 입니다.")
    private String password;

}
