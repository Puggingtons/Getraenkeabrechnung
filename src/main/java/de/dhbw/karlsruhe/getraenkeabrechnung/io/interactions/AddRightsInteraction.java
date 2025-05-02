package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.rights.Right;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

import java.util.HashSet;
import java.util.Set;

public class AddRightsInteraction extends Interaction<User> {
    private final StringInput usernameInput;
    private final StringInput rightsInput;
    private final UserDatabase userDatabase;
    
    public AddRightsInteraction(UserDatabase userDatabase) {
        usernameInput = new StringInput("Username: ");
        rightsInput = new StringInput("Rights to add (comma separated, available: CAN_CREATE_NEW_USER, CAN_SELF_INVOICE, CAN_DELETE_USER, CAN_CREATE_DRINK, CAN_ADD_RIGHTS): ");
        this.userDatabase = userDatabase;
    }
    
    @Override
    String usage() {
        return "Assign specific rights to a user. You can choose from the following rights:\n" +
                "- CAN_CREATE_NEW_USER: Allows creating new users\n" +
                "- CAN_SELF_INVOICE: Allows self-invoicing\n" +
                "- CAN_DELETE_USER: Allows deleting users\n" +
                "- CAN_CREATE_DRINK: Allows creating new drink options\n" +
                "- CAN_ADD_RIGHTS: Allows adding rights to other users\n" +
                "Enter rights as a comma-separated list (e.g., 'CAN_CREATE_NEW_USER,CAN_CREATE_DRINK')";
    }
    
    @Override
    protected void execute() {
        // Get username
        String username = getValidInput(usernameInput);
        Username usernameObj = new Username(username);
        
        // Check if user exists
        if (!userDatabase.userExists(usernameObj)) {
            System.out.println("User does not exist!");
            failure();
            return;
        }
        
        // Get the user
        User user = null;
        for (User u : userDatabase.getUsers()) {
            if (u.getUsername().equals(usernameObj)) {
                user = u;
                break;
            }
        }
        
        if (user == null) {
            System.out.println("Failed to retrieve user information!");
            failure();
            return;
        }
        
        // Get rights to add
        String rightsString = getValidInput(rightsInput);
        Set<Right> rightsToAdd = parseRights(rightsString);
        
        if (rightsToAdd.isEmpty()) {
            System.out.println("No valid rights specified!");
            failure();
            return;
        }
        
        // Add rights to user
        user.addRights(rightsToAdd);
        
        // Display assigned rights
        System.out.println("Rights successfully assigned to user " + username + ":");
        for (Right right : rightsToAdd) {
            System.out.println("- " + right);
        }
        
        // Return the updated user
        success(user);
    }
    
    private Set<Right> parseRights(String rightsString) {
        Set<Right> rights = new HashSet<>();
        String[] rightStrings = rightsString.split(",");
        
        for (String rightString : rightStrings) {
            String trimmed = rightString.trim();
            try {
                Right right = Right.valueOf(trimmed);
                rights.add(right);
            } catch (IllegalArgumentException e) {
                System.out.println("Warning: Invalid right '" + trimmed + "' ignored");
            }
        }
        
        return rights;
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
