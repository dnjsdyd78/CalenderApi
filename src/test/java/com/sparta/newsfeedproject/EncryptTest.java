package com.sparta.newsfeedproject;


import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EncryptTest {

    @Test
    void test1(){
        System.out.println(BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, "qwer123".toCharArray()));
    }
}
