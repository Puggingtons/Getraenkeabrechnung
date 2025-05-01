package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;
import de.dhbw.karlsruhe.getraenkeabrechnung.validators.UsernameValidator;
import de.dhbw.karlsruhe.getraenkeabrechnung.validators.PasswordValidator;

public class RegisterUserInteraction extends Interaction<User> {

    private final StringInput usernameInput;
    private final StringInput passwordInput;
    private final StringInput passwordVerificationInput;
    private final UserDatabase userDatabase;

    public RegisterUserInteraction(UserDatabase userDatabase) {
        usernameInput = new StringInput("Username: ");
        passwordInput = new StringInput("Password: ");
        passwordVerificationInput = new StringInput("Verify Password: ");
        this.userDatabase = userDatabase;
    }

    @Override
    String usage() {
        return "Please enter a username and a password.";
    }

    @Override
    public void execute() {
        String username = getValidInput(usernameInput);
        String password = getValidInput(passwordInput);
        String passwordVerification = getValidInput(passwordVerificationInput);
        userDatabase.getUsers();

        User user = new User();

        // Check if user exists
        Username usernameObj = new Username(username);
        Password passwordObj = new Password(password);
        if (userDatabase.userExists(usernameObj)) {
            System.out.println("Username already exists!");
            failure();
            return;
        }

        if (!password.equals(passwordVerification)) {
            System.out.println("Passwords do not match!");
            failure();
            return;
        }

        user.setUsername(usernameObj);
        user.setPassword(passwordObj);

        if (!UsernameValidator.isValidUsername(user.getUsername()) || !PasswordValidator.isValidPassword(user.getPassword())) {
            failure();
        } else {
            success(user);
            user.nullPassword();
        }
    }

    private String getValidInput(StringInput input) {
        while (true) {
            Result<String> result = input.prompt();

            if (result.isHelp()) {
                explain();
                continue;
            }

            if (result.isNone()) {
                System.out.println("Invalid input!");
                continue;
            }

            return result.getValue();
        }
    }
}
