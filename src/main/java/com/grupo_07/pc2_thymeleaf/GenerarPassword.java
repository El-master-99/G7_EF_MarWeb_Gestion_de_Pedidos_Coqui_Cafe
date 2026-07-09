package com.grupo_07.pc2_thymeleaf;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarPassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = "123456";
        String hash = encoder.encode(password);

        System.out.println("Hash BCrypt: " + hash);
    }
}