package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.rights.Right;

public class LoggedInUserInteractionFactory {
    private final ThirstyCalc thirstyCalc;

    private MenuInteraction menuInteraction;

    public LoggedInUserInteractionFactory(ThirstyCalc thirstyCalc) {
        this.thirstyCalc = thirstyCalc;
    }

    public Interaction<?> build() {
        menuInteraction = new MenuInteraction();

        addDefaultInteractions();
        addRoleDefinedInteractions();
        addCheckBalanceInteraction();

        return menuInteraction;
    }

    private void addDefaultInteractions() {
        addLogoutInteraction();
        addChangePasswordInteraction();
    }

    private void addRoleDefinedInteractions() {
        User loggedInUser = thirstyCalc.getApplicationState().getLoggedInUser();
        
        // Only add admin interactions if the user has the required rights
        if (loggedInUser != null) {
            if (loggedInUser.hasRight(Right.CAN_DELETE_USER)) {
                addDeleteUserInteraction();
            }
            
            if (loggedInUser.hasRight(Right.CAN_CREATE_DRINK)) {
                addCreateDrinkOptionInteraction();
            }
            if (loggedInUser.hasRight(Right.CAN_CREATE_NEW_USER)) {
                addCreateUserInteraction();
            }

            if (loggedInUser.hasRight(Right.CAN_CREATE_CATEGORY)) {
                addCreateCategoryOptionInteraction();
            }

            if (loggedInUser.hasRight(Right.CAN_ADD_RIGHTS)) {
                addAddRightsInteraction();
            }
        }
    }
    
    private void addLogoutInteraction() {
        LogoutInteraction interaction = new LogoutInteraction();
        interaction.onSuccess(event -> {
            thirstyCalc.logout();
            menuInteraction.stop();
        });
        menuInteraction.addInteraction("logout", "Logout from your account", interaction);
    }

    private void addChangePasswordInteraction() {
        ChangePasswordInteraction interaction = new ChangePasswordInteraction(thirstyCalc.getApplicationState().getLoggedInUser(), thirstyCalc.getUserDatabase());
        interaction.onSuccess((changePassword) -> {
            thirstyCalc.changePassword(changePassword);
        });
        menuInteraction.addInteraction("change-password", "Change your password.", interaction);
    }

    private void addCheckBalanceInteraction() {
        CheckBalanceInteraction interaction = new CheckBalanceInteraction(thirstyCalc.getAccountOfLoggedInUser());
        interaction.onSuccess((money) -> {
            System.out.println("Your current balance is: " + money + ".");
        });
        menuInteraction.addInteraction("balance", "Shows your current balance.", interaction);
    }

    private void addCreateUserInteraction() {
        CreateUserInteraction interaction = new CreateUserInteraction(thirstyCalc.getUserDatabase());
        interaction.onSuccess((createUser) -> {
            thirstyCalc.createNewUser(createUser);
            System.out.println("User " + createUser.getUsername().toString() + " created.");
        });
        menuInteraction.addInteraction("create-user", "Create a user and grant him rights.", interaction);
    }

    private void addDeleteUserInteraction() {
        DeleteUserInteraction interaction = new DeleteUserInteraction(thirstyCalc);
        interaction.onSuccess((deleteUser) -> {
            thirstyCalc.deleteUser(deleteUser);
            System.out.println("You deleted the user: " + deleteUser.getUsername().toString() + ".");
        });
        menuInteraction.addInteraction("delete-user", "Delete a user", interaction);
    }

    private void addCreateDrinkOptionInteraction() {
        CreateDrinkOptionInteraction interaction = new CreateDrinkOptionInteraction(thirstyCalc);
        interaction.onSuccess((drinkOption) -> {
            thirstyCalc.createNewDrinkOption(drinkOption);
            System.out.println("You created: " + drinkOption.getDrinkName().toString() + ", its color is: " + drinkOption.getColorName().toString() + ".");
        });
        menuInteraction.addInteraction("create-drink", "Create a new drink option.", interaction);
    }

    private void addCreateCategoryOptionInteraction() {
        CreateCategoryOptionInteraction interaction = new CreateCategoryOptionInteraction(thirstyCalc);
        interaction.onSuccess((categoryOption) -> {
            thirstyCalc.createNewCategoryOption(categoryOption);
            System.out.println("You created: " + categoryOption.getColorName().toString() + ", its price is: " + categoryOption.getColorPrice() + ".");
        });
        menuInteraction.addInteraction("create-category", "Create a new category option.", interaction);
    }
    
    private void addAddRightsInteraction() {
        AddRightsInteraction interaction = new AddRightsInteraction(thirstyCalc.getUserDatabase());
        interaction.onSuccess((updatedUser) -> {
            boolean updated = thirstyCalc.getUserDatabase().updateUser(updatedUser);
            if (updated) {
                System.out.println("User rights updated successfully.");
            } else {
                System.out.println("Failed to update user rights in the database.");
            }
        });
        menuInteraction.addInteraction("add-rights", "Assign rights to a user", interaction);
    }
}
