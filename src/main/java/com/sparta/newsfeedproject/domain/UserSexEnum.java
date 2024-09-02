package com.sparta.newsfeedproject.domain;

import lombok.Getter;

@Getter
public enum UserSexEnum {
    MALE(Authority.MALE),  // 사용자 권한
    FEMALE(Authority.FEMALE);  // 관리자 권한

    private final String authority;

    UserSexEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String MALE = "MAIL";
        public static final String FEMALE = "FEMAIL";
    }
}