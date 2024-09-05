package com.sparta.newsfeedproject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.PARAMETER) // 파라미터를 받을 때만 사용가능하도록
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    // 애노테이션 정의
}
