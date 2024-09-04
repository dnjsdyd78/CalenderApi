package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.config.JwtUtil;
import com.sparta.newsfeedproject.config.PasswordEncoder;
import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.dto.UserWithdrawalRequestDto;
import com.sparta.newsfeedproject.dto.request.UserLoginRequestDto;
import com.sparta.newsfeedproject.dto.request.UserUpdateRequestDto;
import com.sparta.newsfeedproject.dto.response.UserResponseDto;
import com.sparta.newsfeedproject.exception.DeleteException;
import com.sparta.newsfeedproject.exception.InvalidPasswordException;
import com.sparta.newsfeedproject.exception.NotFoundException;
import com.sparta.newsfeedproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwttuil;


    public UserResponseDto updateUser(User tokenUser, UserUpdateRequestDto userUpdateRequestDto) {

        User user = userRepository.findById(tokenUser.getId()).orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        user.userUpdate(userUpdateRequestDto);

        return UserResponseDto.builder()
                .id(user.getId())
                .build();

    }

    public UserResponseDto login(UserLoginRequestDto userLoginRequestDto) {

        User user = userRepository.findByEmail(userLoginRequestDto.getEmail()).orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        boolean checkPassword = passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword());

        if (checkPassword) {

            return UserResponseDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .birth(user.getBirth())
                    .gender(user.getGender())
                    .build();

        } else {
            // 비밀번호 미일치 exception
            throw new InvalidPasswordException(HttpStatus.UNAUTHORIZED, "비밀번호를 확인해주세요.");
        }
    }

    public void userWithdrawal(User tokenUser, UserWithdrawalRequestDto userWithdrawalRequestDto) {
        User user = userRepository.findById(tokenUser.getId()).orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        try {
            userRepository.delete(user);
        } catch (DeleteException e) {
            throw new DeleteException(HttpStatus.INTERNAL_SERVER_ERROR, "삭제도중 알수없는 오류가 발생하였습니다.");
        }
    }

    public UserResponseDto getProfile(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 유저가 없습니다."));


        return new UserResponseDto(findUser);
    }
}
