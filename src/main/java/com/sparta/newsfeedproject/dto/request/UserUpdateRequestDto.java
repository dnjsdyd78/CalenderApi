package com.sparta.newsfeedproject.dto.request;


import com.sparta.newsfeedproject.domain.UserGenderEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {

    @Email
    private String email;

    private String userName;

    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}", message = "전화번호 형식이 올바르지 않습니다.")
    private String phoneNumber;

    @Past(message = "생년월일은 과거 날짜여야 합니다.")
    private LocalDate birth;

    private UserGenderEnum gender;

}
