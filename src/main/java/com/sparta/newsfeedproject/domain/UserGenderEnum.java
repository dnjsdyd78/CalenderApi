package com.sparta.newsfeedproject.domain;

import lombok.Getter;

@Getter
public enum UserGenderEnum {
    MALE(Authority.MALE),  // 사용자 권한
    FEMALE(Authority.FEMALE);  // 관리자 권한

    private final String authority;

    UserGenderEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String MALE = "MALE";
        public static final String FEMALE = "FEMALE";
    }
}