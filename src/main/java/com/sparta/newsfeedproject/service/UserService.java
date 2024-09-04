package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.dto.request.UserDto;
import com.sparta.newsfeedproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User signUp(UserDto userDto) {
        User newUser = new User();
        newUser.setUserName(userDto.getUserName());
        newUser.setPassword(userDto.getPassword());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        newUser.setIdEmail(userDto.getIdEmail());
        newUser.setBirth(userDto.getBirth());
        newUser.setGender(userDto.getGender());

        return userRepository.save(newUser);
    }



}
