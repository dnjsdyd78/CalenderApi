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

@Slf4j(topic = "userLoginController")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
  
    @PostMapping("/user/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        userService.signUp(userDto);
        return ResponseEntity.ok("회원가입 완료");
    }



    @PatchMapping("/profile/update")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> updateProfile(@Auth UserTokenDto tokenUser, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {

        UserResponseDto userResponseDto = userService.updateUser(tokenUser, userUpdateRequestDto);

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), userResponseDto), HttpStatus.OK);

    }

    @PostMapping("/user/login")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> userLogin(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto){

        UserResponseDto userResponseDto = userService.login(userLoginRequestDto);

        String token = jwtService.createToken(userResponseDto.getId(), userResponseDto.getEmail());

        return ResponseEntity.ok()
                .header(JwtConfig.AUTHORIZATION_HEADER, token)
                .body(new CommonResponseDto<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), userResponseDto));

    }

    @PostMapping("/user/withdrawal")
    public ResponseEntity<CommonResponseDto<Void>> userWithdrawal(@Auth UserTokenDto tokenUser, @Valid @RequestBody UserWithdrawalRequestDto userWithdrawalRequestDto){

        userService.userWithdrawal(tokenUser, userWithdrawalRequestDto);

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<CommonResponseDto> getProfile(@Auth UserTokenDto Token, @PathVariable Long id){
        UserResponseDto responseDto = userService.getProfile(id);

        return new ResponseEntity<>(new CommonResponseDto<>(200, "success", responseDto), HttpStatus.OK);
    }

    @PostMapping("/{userId}/profile/password")
    public void updatePassword(@Auth UserTokenDto userTokenDto, @RequestParam String newPassword) {
        userService.updatePassword(userTokenDto, newPassword);
    }
}
