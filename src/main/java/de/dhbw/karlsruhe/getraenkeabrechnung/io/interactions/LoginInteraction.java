package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class LoginInteraction extends Interaction<User> {

    private final StringInput usernameInput;
    private final StringInput passwordInput;

    public LoginInteraction() {
        super();
        usernameInput = new StringInput("Username> ");
        passwordInput = new StringInput("Password> ");
    }

    @Override
    public void explain() {
        System.out.println("Please enter your username and password to login.");
    }

    @Override
    public void execute() {
        String username = getValidInput(usernameInput);
        String password = getValidInput(passwordInput);

        // todo check if username and password exist and are valid

        // todo: this is temporary
        if (username.equals("Hans")) {
            System.out.println("User does not exist");
            failure();
        }

        if (password.equals("ff")) {
            System.out.println("Password is incorrect");
            failure();
        }

        User user = new User(new Username(username), new Password(password));
        success(user);
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
