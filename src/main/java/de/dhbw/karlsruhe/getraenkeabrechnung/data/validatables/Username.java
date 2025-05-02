package de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables;

import java.util.Optional;
import java.util.regex.Pattern;

public class Username implements Validatable {

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

    @Override
    public String toString() {
        return this.usernameString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username = (Username) o;
        return usernameString.equals(username.usernameString);
    }

}

