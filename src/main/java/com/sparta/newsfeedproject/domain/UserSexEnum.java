package com.sparta.newsfeedproject.domain;

import lombok.Getter;

@Getter
public enum UserSexEnum {
    USER(Authority.MAIL),  // 사용자 권한
    ADMIN(Authority.FEMAIL);  // 관리자 권한

    private final String authority;

    UserSexEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String MAIL = "MAIL";
        public static final String FEMAIL = "FEMAIL";
    }
}