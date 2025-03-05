package de.dhbw.karlsruhe.getraenkeabrechnung;

import java.util.Optional;
import java.util.regex.Pattern;

public class Username {

    private final String usernameString;

    public Username(String username) {
        this.usernameString = username;
    }

    public boolean isValid(Optional<String> pattern) {

        if (pattern.isEmpty()) {
            return false;
        }

        Pattern compiledUsernamePattern = Pattern.compile(pattern.get()); 

        return compiledUsernamePattern.matcher(this.usernameString).matches();
    }

}

