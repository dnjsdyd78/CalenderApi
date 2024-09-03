package com.sparta.newsfeedproject.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDto {

    @NotNull(message = "email 은 필수 항목입니다.")
    @NotBlank(message = "email 은 필수 항목입니다.")
    private String email;

    @NotNull(message = "비밀번호는 필수 항목입니다.")
    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;

}
