package com.sparta.newsfeedproject.dto.request;


import com.sparta.newsfeedproject.domain.UserGenderEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String email;

    private String userName;

    private String phoneNumber;

    private LocalDate birth;

    private String password;

    private UserGenderEnum gender;

}
