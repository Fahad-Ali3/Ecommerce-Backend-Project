package com.momsdeli.backend.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder cryptPasswordEncoder=new BCryptPasswordEncoder();
        System.out.println(cryptPasswordEncoder.encode("khan12"));
        System.out.println(cryptPasswordEncoder.encode("Saeed124@"));
    }
}
