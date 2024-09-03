package com.sparta.newsfeedproject.controller;


import com.sparta.newsfeedproject.dto.response.CommonResponseDto;
import com.sparta.newsfeedproject.dto.response.UserResponseDto;
import com.sparta.newsfeedproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<CommonResponseDto> getProfile(@PathVariable Long id){
        UserResponseDto responseDto = userService.getProfile(id);

        return new ResponseEntity<>(new CommonResponseDto<>(200, "success", responseDto), HttpStatus.OK);
    }
}
