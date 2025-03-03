package de.dhbw.karlsruhe.getraenkeabrechnung;

import java.util.regex.Pattern;


public class UsernameValidator {

    // credits: https://mkyong.com/regular-expressions/how-to-validate-username-with-regular-expression/

    // Username requirements:
    // Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
    // Username allowed of the dot (.), underscore (_), and hyphen (-).
    // The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
    // The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex
    // The number of characters must be between 5 to 20.

    private static final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

    private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

    public static boolean isValidUsername(Username username) {
        if (username == null) {
            return false;
        }

        boolean isValidUsernameMatch = pattern.matcher(username.toString()).matches();

        if (!isValidUsernameMatch) {
            System.out.println("Username does not match pattern! The username must be between 5 and 20 characters long, contain only alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase, and may contain dots (.), underscores (_), or hyphens (-). The dot (.), underscore (_), or hyphen (-) must not be the first or last character and must not appear consecutively.");
        }

        return isValidUsernameMatch;
    }

    // Test policy of username according to regex rules
    public static void main(String[] args) {
        String[] testUsernames = {"valid_User123", "_invalidUser123", "valid.User1234", "invalid..user1234", "inv."
        };

        for (String username : testUsernames) {
            System.out.println("Test: " + username + " → " + isValidUsername(new Username(username)));
        }
    }
}

