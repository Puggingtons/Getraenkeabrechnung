package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;
import de.dhbw.karlsruhe.getraenkeabrechnung.validators.UsernameValidator;
import de.dhbw.karlsruhe.getraenkeabrechnung.validators.PasswordValidator;

public class CreateUserInteraction extends Interaction<User> {

    private final StringInput usernameInput;
    private final StringInput passwordInput;
    private final StringInput passwordVerificationInput;

    public CreateUserInteraction() {
        usernameInput = new StringInput("Username: ");
        passwordInput = new StringInput("Password: ");
        passwordVerificationInput = new StringInput("Verify Password: ");
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

        // todo: check user database
        if (username.equals("Hans")) {
            System.out.println("Username already exists!");
            failure();
            return;
        }

        if (!password.equals(passwordVerification)) {
            System.out.println("Passwords do not match!");
            failure();
            return;
        }

        User user = new User(new Username(username), new Password(password));

        if (!UsernameValidator.isValidUsername(user.getUsername())) {
            failure();
        } else if (!PasswordValidator.isValidPassword(user.getPassword())) {
            failure();
        } else {
            success(user);
        }

        // success(user);
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
