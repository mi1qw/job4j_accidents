package com.example.job4j_accidents;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ScreenEncoder {
    public static void main(final String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("123");
        System.out.println(pwd);
    }
}
