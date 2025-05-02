package de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.validators;

import java.util.Optional;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Validatable;


public class UsernameValidator {

    // credits: https://mkyong.com/regular-expressions/how-to-validate-username-with-regular-expression/

    // Username requirements:
    // 1. Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
    // 2. Username allowed of the dot (.), underscore (_), and hyphen (-).
    // 3. The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
    // 4. The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex
    // 5. The number of characters must be between 5 to 20.

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

    public static boolean isValid(Validatable username) {
        if (!(username instanceof Username)) {
            return false;
        }

        boolean validUsername = username.isValid(Optional.of(USERNAME_PATTERN));

        if (!validUsername) {
            System.out.println("Username does not match pattern! The username must be between 5 and 20 characters long, contain only alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase, and may contain dots (.), underscores (_), or hyphens (-). The dot (.), underscore (_), or hyphen (-) must not be the first or last character and must not appear consecutively.");
        }

        return validUsername;
    }

}

