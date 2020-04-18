package com.dynamic.register.common.utils;

import org.apache.commons.text.RandomStringGenerator;

public class PasswordGenerator {

    public static String generatePassword(){
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
                .build();
        return pwdGenerator.generate(8);
    }
}
