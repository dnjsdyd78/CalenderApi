package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.dto.request.*;
import com.sparta.newsfeedproject.config.PasswordEncoder;
import com.sparta.newsfeedproject.dto.response.UserResponseDto;
import com.sparta.newsfeedproject.exception.DeletedAccountException;
import com.sparta.newsfeedproject.exception.DeletedException;
import com.sparta.newsfeedproject.exception.InvalidPasswordException;
import com.sparta.newsfeedproject.exception.NotFoundException;
import com.sparta.newsfeedproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
  
    @Transactional
    public User signUp(UserDto userDto) {
        User newUser = new User();
        newUser.setUserName(userDto.getUserName());
        newUser.setPassword(userDto.getPassword());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        newUser.setEmail(userDto.getIdEmail());
        newUser.setBirth(userDto.getBirth());
        newUser.setGender(userDto.getGender());

        return userRepository.save(newUser);
    }

    @Transactional
    public UserResponseDto updateUser(UserTokenDto tokenUser, UserUpdateRequestDto userUpdateRequestDto) {

        User user = userRepository.findById(tokenUser.getUserId()).orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

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

    @Transactional
    public void userWithdrawal(UserTokenDto tokenUser, UserWithdrawalRequestDto userWithdrawalRequestDto) {

        Optional<User> deleteUser = userRepository.findDeletedUserById(tokenUser.getUserId());
        if(deleteUser.isPresent()) throw new DeletedAccountException(HttpStatus.BAD_REQUEST, "이미 삭제된 계정입니다.");

        User user = userRepository.findById(tokenUser.getUserId()).orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        try {
            boolean checkPassword = passwordEncoder.matches(userWithdrawalRequestDto.getPassword(), user.getPassword());
            if (checkPassword) {
                userRepository.delete(user);
            } else {
                throw new InvalidPasswordException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
            }
        } catch (InvalidPasswordException e) {
            throw new InvalidPasswordException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        } catch (RuntimeException e) {
            throw new DeletedException(HttpStatus.INTERNAL_SERVER_ERROR, "삭제도중 알수없는 오류가 발생하였습니다.");
        }
    }

    public UserResponseDto getProfile(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 유저가 없습니다."));


        return new UserResponseDto(findUser);
    }

    // 사용자 check
    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 id 값을 가진 유저를 찾을 수 없습니다."));
    }
}
