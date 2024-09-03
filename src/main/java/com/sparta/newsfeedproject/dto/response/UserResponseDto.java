package com.sparta.newsfeedproject.dto.response;

import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.domain.UserGenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate birth;
    private UserGenderEnum gender;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getUserName();
        this.email = user.getEmail();
        this.birth = user.getBirth();
        this.gender = user.getGender();
    }
}
