package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.dto.response.UserResponseDto;
import com.sparta.newsfeedproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserResponseDto getProfile(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 유저가 없습니다."));


        return new UserResponseDto(findUser);
    }
}
