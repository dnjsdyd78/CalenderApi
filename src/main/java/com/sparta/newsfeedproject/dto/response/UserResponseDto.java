package com.sparta.newsfeedproject.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.domain.UserGenderEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {

    private Long id;

    private String email;

    private String userName;

    private String password;

    private String phoneNumber;

    private LocalDate birth;

    private UserGenderEnum gender;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.birth = user.getBirth();
        this.gender = user.getGender();
    }
}
