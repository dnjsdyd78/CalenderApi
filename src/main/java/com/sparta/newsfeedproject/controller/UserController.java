package com.sparta.newsfeedproject.controller;


import com.sparta.newsfeedproject.dto.request.*;
import com.sparta.newsfeedproject.annotation.Auth;
import com.sparta.newsfeedproject.config.JwtConfig;
import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.dto.response.CommonResponseDto;
import com.sparta.newsfeedproject.dto.response.UserResponseDto;
import com.sparta.newsfeedproject.service.JwtService;
import com.sparta.newsfeedproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j(topic = "userLoginController")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
  
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signUp(@Valid @RequestBody UserDto userDto) {
        userService.signUp(userDto);
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", 200);
        response.put("message", "success");

        // 응답을 JSON 형태로 반환
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/update")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> updateProfile(@Auth UserTokenDto tokenUser, @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {

        UserResponseDto userResponseDto = userService.updateUser(tokenUser, userUpdateRequestDto);

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), userResponseDto), HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> userLogin(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto){

        UserResponseDto userResponseDto = userService.login(userLoginRequestDto);

        String token = jwtService.createToken(userResponseDto.getId(), userResponseDto.getEmail());

        return ResponseEntity.ok()
                .header(JwtConfig.AUTHORIZATION_HEADER, token)
                .body(new CommonResponseDto<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), userResponseDto));

    }

    @PostMapping("/withdrawal")
    public ResponseEntity<CommonResponseDto<Void>> userWithdrawal(@Auth UserTokenDto tokenUser, @Valid @RequestBody UserWithdrawalRequestDto userWithdrawalRequestDto){

        userService.userWithdrawal(tokenUser, userWithdrawalRequestDto);

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDto> getProfile(@Auth UserTokenDto Token, @PathVariable Long id){
        UserResponseDto responseDto = userService.getProfile(id);

        return new ResponseEntity<>(new CommonResponseDto<>(200, "success", responseDto), HttpStatus.OK);
    }

    @PostMapping("/{userId}/profile/password")
    public void updatePassword(@Auth UserTokenDto userTokenDto, @RequestParam String newPassword) {
        userService.updatePassword(userTokenDto, newPassword);
    }
}
