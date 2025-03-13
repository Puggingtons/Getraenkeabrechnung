package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;

public class CreateUserInteraction implements Interaction {
    @Override
    public void explain() {
        System.out.println("Please enter a username and a password.");
    }

    @Override
    public void run() {
        StringInput usernameInput = new StringInput("Username: ");
        StringInput passwordInput = new StringInput("Password: ");
        StringInput passwordVerificationInput = new StringInput("Verify Password: ");

        boolean success = false;

        while (!success) {
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
                continue;
            }

            // todo: createUser in user database

            success = true;
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
