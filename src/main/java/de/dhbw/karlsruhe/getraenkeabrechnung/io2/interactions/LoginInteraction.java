package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;

public class LoginInteraction implements Interaction<User> {
    @Override
    public void explain() {

    }

    @Override
    public User run() {
        StringInput usernameInput = new StringInput("Username: ");
        StringInput passwordInput = new StringInput("Password: ");

        while (true) {
            String username = getValidInput(usernameInput);
            String password = getValidInput(passwordInput);

            // todo check if username and password exist and are valid

            // todo: this is temporary
            if (username.equals("Hans")) {
                System.out.println("User does not exist");
                continue;
            }

            if (password.equals("ff")) {
                System.out.println("Password is incorrect");
                continue;
            }

            return new User(new Username(username), new Password(password));
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
