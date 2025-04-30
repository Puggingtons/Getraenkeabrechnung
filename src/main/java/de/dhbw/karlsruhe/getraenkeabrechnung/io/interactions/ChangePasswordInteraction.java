package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class ChangePasswordInteraction extends Interaction<User> {
    
    private final StringInput oldPasswordInput;
    private final StringInput newPasswordInput;
    private final StringInput newPasswordVerificationInput;
    private final User user;
    private final UserDatabase userDatabase;

    public ChangePasswordInteraction(User user, UserDatabase userDatabase) {
        oldPasswordInput = new StringInput("Old Password: ");
        newPasswordInput = new StringInput("New Password: ");
        newPasswordVerificationInput = new StringInput("Confirm new Password: ");
        this.user = user;
        this.userDatabase = userDatabase;
    }

    @Override
    String usage() {
        return "Please enter your old password and a new password.";
    }

    @Override
    public void execute() {
        String oldPassword = getValidInput(oldPasswordInput);
        String newPassword = getValidInput(newPasswordInput);
        String newPasswordVerification = getValidInput(newPasswordVerificationInput);

        User foundUser = null;
        for (User user : userDatabase.getUsers()) {
            if (user.getUsername().equals(this.user.getUsername())) {
                foundUser = user;
                break;
            }
        }

        try {
            if (foundUser == null || !user.verifyPassword(oldPassword)) {
                System.out.println("Old password is incorrect!");
                failure();
                return;
            }
        } catch (Exception e) {
            System.out.println("Error verifying password: " + e.getMessage());
            failure();
            return;
        }

        if (!newPassword.equals(newPasswordVerification)) {
            System.out.println("New passwords do not match!");
            failure();
            return;
        }

        try {
            Password newPasswordObj = new Password(newPassword);
            foundUser.setPassword(newPasswordObj);
            System.out.println("Password successfully updated!");
            success(foundUser);

        } catch (IllegalArgumentException e) {
            System.out.println("Error updating password: " + e.getMessage());
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
                System.out.println("Invalid input!");
                continue;
            }

            return result.getValue();
        }
    }
}
