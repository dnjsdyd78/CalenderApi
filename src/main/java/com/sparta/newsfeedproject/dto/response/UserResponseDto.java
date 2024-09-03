package com.sparta.newsfeedproject.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
// null값은 JSON응답에서 제외
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate birth;
    private UserGenderEnum gender;
}
