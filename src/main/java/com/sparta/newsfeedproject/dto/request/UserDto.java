package com.sparta.newsfeedproject.dto.request;


import com.sparta.newsfeedproject.domain.UserGenderEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor

public class UserDto {

  @NotNull(message = "이름을 채워주세요.")
  private String userName;

  @Pattern(
      regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,15}",
      message = "비밀번호는 8자 이상 15자 이하로, 대문자, 소문자, 숫자, 특수문자를 각각 최소 1개씩 포함해야 합니다."
  )
  @Size(min = 8, max = 15, message = "비밀번호는 8자이상 15자이하여야 합니다.")
  private String password;

  @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}", message = "전화번호 형식이 올바르지 않습니다.")
  private String phoneNumber;

  @NotNull(message = "성별을 입력해주세요.")
  public UserGenderEnum gender;

  @Past(message = "생년월일은 과거 날짜여야 합니다.")
  private LocalDate birth;

  @Email
  private String email;

}
