package com.dynamic.register.common.utils;

import org.apache.commons.text.RandomStringGenerator;

public class PasswordGenerator {

    private static final char[][] PAIRS = {{'a', 'z'}, {'0', '9'}, {'A', 'Z'}};

    public static String generatePassword() {
        return new RandomStringGenerator.
                Builder().
                withinRange(33, 45).
                withinRange(PAIRS).
                build().
                generate(6);

    }
}
