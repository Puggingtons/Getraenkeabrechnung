package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.AccountDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class DeleteUserInteraction extends Interaction<User>{
    private final StringInput usernameInput;
    private final StringInput usernameVerificationInput;
    private final UserDatabase userDatabase;
    private final AccountDatabase accountDatabase;
    private final ThirstyCalc thirstyCalc;

    public DeleteUserInteraction(UserDatabase userDatabase, AccountDatabase accountDatabase, ThirstyCalc thirstyCalc) {
        usernameInput = new StringInput("Username: ");
        usernameVerificationInput = new StringInput("Confirm Username (WARNING: THIS CANNOT BE UNDONE): ");
        this.userDatabase = userDatabase;
        this.accountDatabase = accountDatabase;
        this.thirstyCalc = thirstyCalc;
    }

    @Override
    String usage() {
        return "Please enter the username of the user you want to delete.";
    }

    @Override
    protected void execute() {
        String username = getValidInput(usernameInput);
        String usernameVerification = getValidInput(usernameVerificationInput);
        userDatabase.getUsers();
        accountDatabase.getAccounts();
        Username usernameObj = new Username(username);

        if (!username.equals(usernameVerification)) {
            System.out.println(
                "Usernames do not match!"
                );
            failure();
            return;
        }

        // Check if user exists
        if (!userDatabase.userExists(usernameObj)) {
            System.out.println(
                "User does not exist!"
                );
            failure();
            return;
        }

        // Check if user is trying to delete themselves
        User loggedInUser = thirstyCalc.getApplicationState().getLoggedInUser();
        if (loggedInUser != null && loggedInUser.getUsername().equals(usernameObj)) {
            System.out.println(
                "You cannot delete yourself!"
                );
            failure();
            return;
        }

        // Check balance of the user
        User user = null;
        for (User u : userDatabase.getUsers()) {
            if (u.getUsername().equals(usernameObj)) {
                user = u;
                break;
            }
        }
        if (user != null && !accountDatabase.checkIfAccountBalanceIsZero(user)) {
            System.out.println(
                "User has a positive balance. Please settle the balance before deleting the user."
                );
            failure();
            return;
        }

        // Find the user in the database
        User foundUser = null;
        for (User u : userDatabase.getUsers()) {
            if (u.getUsername().equals(usernameObj)) {
                foundUser = user;
                break;
            }
        }

        // Delete the user
        if (foundUser != null) {
            System.out.println(
                "User deleted successfully!"
                );
            success(foundUser);
        } else {
            System.out.println(
                "User not found"
                );
            failure();
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
                System.out.println(
                    "Invalid input!"
                    );
                continue;
            }

            return result.getValue();
        }
    }
}
