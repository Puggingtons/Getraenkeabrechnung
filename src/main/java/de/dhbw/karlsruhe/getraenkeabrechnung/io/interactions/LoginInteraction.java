package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.PasswordManagementException;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class LoginInteraction extends Interaction<User> {

    private final StringInput usernameInput;
    private final StringInput passwordInput;
    private final UserDatabase userDatabase;

    public LoginInteraction(UserDatabase userDatabase) {
        super();
        usernameInput = new StringInput("Username> ");
        passwordInput = new StringInput("Password> ");
        this.userDatabase = userDatabase;
    }

    @Override
    String usage() {
        return "Please enter your username and password to login.";
    }

    @Override
    public void execute() {
        String username = getValidInput(usernameInput);
        String password = getValidInput(passwordInput);
        userDatabase.getUsers();

        // Check if user exists
        Username usernameObj = new Username(username);
        if (!userDatabase.userExists(usernameObj)) {
            System.out.println("User does not exist");
            failure();
            return;
        }

        // Find the user in the database
        User foundUser = null;
        for (User user : userDatabase.getUsers()) {
            if (user.getUsername().equals(usernameObj)) {
                foundUser = user;
                break;
            }
        }

        // Verify password
        try {
            if (foundUser == null || !Password.verifyPassword(password, foundUser.getHashedPassword(), foundUser.getSalt())) {
                System.out.println("Password is incorrect");
                failure();
                return;
            }
        } catch (PasswordManagementException e) {
            System.out.println("Error verifying password: " + e.getMessage());
            failure();
            return;
        }

        // Login successful
        System.out.println("Login successful!");
        success(foundUser);
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
