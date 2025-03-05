package de.dhbw.karlsruhe.getraenkeabrechnung;

import java.util.Optional;
import java.util.regex.Pattern;

public class Password {

    private final String passwordString;

    public Password(String password) {
        this.passwordString = password;
    }

    public Boolean isValid(Optional<String> pattern) {

        if (pattern.isEmpty()) {
            return false;
        }

        Pattern compiledPasswordPattern = Pattern.compile(pattern.get());

        return compiledPasswordPattern.matcher(this.passwordString).matches();
    }
}
