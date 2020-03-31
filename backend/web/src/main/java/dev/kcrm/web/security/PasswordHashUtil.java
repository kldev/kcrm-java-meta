package dev.kcrm.web.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public  class PasswordHashUtil {

    public final static BCryptPasswordEncoder passwordEncoder;

    static {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public static String Hash(String value) {
        return passwordEncoder.encode(value);
    }

    public static boolean Match(String raw, String hashed) {
        return passwordEncoder.matches(raw, hashed);
    }
}
