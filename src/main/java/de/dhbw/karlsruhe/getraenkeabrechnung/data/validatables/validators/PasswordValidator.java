package de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.validators;

import java.util.Optional;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Validatable;

public class PasswordValidator {

    // credits: https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation

    // ^                 # start-of-string
    // (?=.*[0-9])       # a digit must occur at least once
    // (?=.*[a-z])       # a lower case letter must occur at least once
    // (?=.*[A-Z])       # an upper case letter must occur at least once
    // (?=.*[@#$%^&+=])  # a special character must occur at least once
    // (?=\S+$)          # no whitespace allowed in the entire string
    // .{8,}             # anything, at least eight places though
    // $                 # end-of-string

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public static boolean isValid(Validatable password) {
        if (!(password instanceof Password)) {
            return false;
        }

        boolean validPassword = password.isValid(Optional.of(PASSWORD_PATTERN));

        if (!validPassword) {
            System.out.println("Password does not match pattern! The password must be at least 8 characters long, contain a number, an upper and lower case letter and a special character (@#$%^&+=).");
        }

        return validPassword;
    }

}
