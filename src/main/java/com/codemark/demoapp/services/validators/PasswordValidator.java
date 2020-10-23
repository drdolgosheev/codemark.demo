package com.codemark.demoapp.services.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private Pattern pattern;
    private Matcher matcher;

    /** >=8 символов
     * содержиться как минимум 1 заглавная и маленькая буква
     * Содержиться как минимум 1 спец символ
     * Нет пробелов табуляций и т.д.
      */
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$\n";

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
