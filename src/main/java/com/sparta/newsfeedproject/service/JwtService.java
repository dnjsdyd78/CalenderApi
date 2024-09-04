package com.sparta.newsfeedproject.service;


import com.sparta.newsfeedproject.config.JwtUtil;
import com.sparta.newsfeedproject.exception.EncodingErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private final JwtUtil jwtUtil;

    public String createToken(Long userId, String email) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);

        String token = jwtUtil.createToken(userId, claims);

        try {
            token = URLEncoder.encode(token, "utf-8").replaceAll("\\+", "%20"); // Cookie Value 에는 공백이 불가능해서 encoding 진행
        } catch (UnsupportedEncodingException e) {
            throw new EncodingErrorException("인코딩에러가 발생하였습니다.");
        }

        return token;
    }



}
