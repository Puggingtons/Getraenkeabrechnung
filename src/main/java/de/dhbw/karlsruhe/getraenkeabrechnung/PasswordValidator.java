package de.dhbw.karlsruhe.getraenkeabrechnung;


import java.util.regex.Pattern;

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

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValidPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password is null");
        }

        boolean isMatch = pattern.matcher(password).matches();

        if (!isMatch) {
            System.out.println("Password does not match pattern! The password must be 8 characters long, contain a number, an upper and lower case letter and a special character (@#$%^&+=).");
        }

        return isMatch;
    }

    // Test policy of username according to regex rules
    public static void main(String[] args) {
        String[] testPasswords = {
            "Short3@", "no!SpeChar1", "NOLOWER#1", "noupper#2", "noNumber#", "goodPassword=1"
        };

        for (String password : testPasswords) {
            System.out.println("Test: " +  password + " → " + isValidPassword(password));
        }
    }

}
