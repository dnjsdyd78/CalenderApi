package com.sparta.newsfeedproject.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.newsfeedproject.config.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String url = httpRequest.getRequestURI();

        // `/v{숫자}/auth`로 시작하는 URL은 필터를 통과하지 않도록 설정
        if (StringUtils.hasText(url) &&
                (
                        // 허용 목록
                        url.startsWith("/api/user/login") ||
                                url.startsWith("/api/user/signup")
                )
        ) {
            chain.doFilter(request, response);
            return;
        }

        // NOTE: 위의 방법이 이해가 어려운 분은 이런 방법을 사용하셔도 좋습니다.
//        if (url.startsWith("/v1/auth") || url.startsWith("/v2/auth")) {
//            chain.doFilter(request, response);
//            return;
//        }

        String bearerJwt = httpRequest.getHeader("Authorization");

        if (bearerJwt == null || !bearerJwt.startsWith("Bearer ")) {
            // 토큰이 없는 경우 400을 반환합니다.
            sendErrorResponse(httpResponse, String.valueOf(HttpServletResponse.SC_BAD_REQUEST), "JWT 토큰이 필요합니다.", null);

//            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "JWT 토큰이 필요합니다.");
            return;
        }

        String jwt = jwtUtil.substringToken(bearerJwt);

        try {
            // JWT 유효성 검사와 claims 추출
            Claims claims = jwtUtil.getUserInfoFromToken(jwt);

            // 사용자 정보를 ArgumentResolver 로 넘기기 위해 HttpServletRequest 에 세팅
            httpRequest.setAttribute("userId", Long.parseLong(claims.getSubject()));
            httpRequest.setAttribute("email", claims.get("email", String.class));

            chain.doFilter(request, response);
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.", e);
            sendErrorResponse(httpResponse, String.valueOf(HttpServletResponse.SC_BAD_REQUEST), "Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.", null);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.", e);
            sendErrorResponse(httpResponse, String.valueOf(HttpServletResponse.SC_BAD_REQUEST), "Expired JWT token, 만료된 JWT token 입니다.", null);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.", e);
            sendErrorResponse(httpResponse, String.valueOf(HttpServletResponse.SC_BAD_REQUEST), "Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.", null);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.", e);
            sendErrorResponse(httpResponse, String.valueOf(HttpServletResponse.SC_BAD_REQUEST), "JWT claims is empty, 잘못된 JWT 토큰 입니다.", null);
        } catch (Exception e) {
            log.error("JWT 토큰 검증 중 오류가 발생했습니다.", e);
            sendErrorResponse(httpResponse, String.valueOf(HttpServletResponse.SC_BAD_REQUEST), "JWT 토큰 검증 중 오류가 발생했습니다.", null);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


    private void sendErrorResponse(HttpServletResponse response, String statusCode, String message, Object data) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // HTTP 상태 코드 설정
        response.setContentType("application/json"); // 응답의 Content-Type을 JSON으로 설정
        response.setCharacterEncoding("UTF-8"); // 응답의 문자 인코딩 설정

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("statusCode", statusCode);
        responseBody.put("message", message);
        responseBody.put("data", data);

        // Map을 JSON 형식으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(responseBody);

        // JSON 응답 작성
        response.getWriter().write(jsonResponse);
    }

}