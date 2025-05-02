package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.BooleanInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.validators.UsernameValidator;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.validators.PasswordValidator;
import de.dhbw.karlsruhe.getraenkeabrechnung.rights.AdminRights;

public class CreateUserInteraction extends Interaction<User> {

    private final StringInput usernameInput;
    private final StringInput passwordInput;
    private final StringInput passwordVerificationInput;
    private final BooleanInput adminRightsInput;
    private final UserDatabase userDatabase;

    public CreateUserInteraction(UserDatabase userDatabase) {
        usernameInput = new StringInput("Username: ");
        passwordInput = new StringInput("Password: ");
        passwordVerificationInput = new StringInput("Verify Password: ");
        adminRightsInput = new BooleanInput("Make user admin? (y/n): ");
        this.userDatabase = userDatabase;
    }

    @Override
    String usage() {
        return "Please enter a username and a password. Optionally, you can make the user an admin.";
    }

    @Override
    protected void execute() {
        String username = getValidInput(usernameInput);
        String password = getValidInput(passwordInput);
        String passwordVerification = getValidInput(passwordVerificationInput);
        boolean adminChoice = getValidInput(adminRightsInput);
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

        // Set admin rights if requested
        if (adminChoice) {
            new AdminRights().giveTo(user);
            System.out.println("Admin rights granted to user " + username);
        }

        if (!UsernameValidator.isValid(user.getUsername())) {
            failure();
        } else if (!PasswordValidator.isValid(user.getPassword())) {
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

    private boolean getValidInput(BooleanInput input) {
        while (true) {
            Result<Boolean> result = input.prompt();

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
