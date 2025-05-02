package de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables;

import java.util.Optional;
import java.util.regex.Pattern;

public class Email implements Validatable {

    private final String emailString;

    public Email(String email) {
        this.emailString = email;
    }

    public boolean isValid(Optional<String> pattern) {

        if (pattern.isEmpty()) {
            return false;
        }   

        Pattern compiledEmailPattern = Pattern.compile(pattern.get());

        return compiledEmailPattern.matcher(this.emailString).matches();
    }
    
}
