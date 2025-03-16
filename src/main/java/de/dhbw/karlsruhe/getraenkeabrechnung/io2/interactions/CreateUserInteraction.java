package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions.event.InteractionEventSource;

public class CreateUserInteraction extends InteractionEventSource<User> implements Interaction<User> {
    @Override
    public void explain() {
        System.out.println("Please enter a username and a password.");
    }

    @Override
    public User run() {
        StringInput usernameInput = new StringInput("Username: ");
        StringInput passwordInput = new StringInput("Password: ");
        StringInput passwordVerificationInput = new StringInput("Verify Password: ");

        while (true) {
            String username = getValidInput(usernameInput);
            String password = getValidInput(passwordInput);
            String passwordVerification = getValidInput(passwordVerificationInput);

            // todo: check user database
            if (username.equals("Hans")) {
                System.out.println("Username already exists!");
                continue;
            }

            if (!password.equals(passwordVerification)) {
                System.out.println("Passwords do not match!");
                failure();
                continue;
            }

            User user = new User(new Username(username), new Password(password));
            success(user);

            // todo: createUser in user database
            return user;
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
