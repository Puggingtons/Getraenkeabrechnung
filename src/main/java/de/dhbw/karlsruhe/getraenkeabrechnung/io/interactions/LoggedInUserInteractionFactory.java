package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
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
    }

    private void addRoleDefinedInteractions() {
        User loggedInUser = thirstyCalc.getApplicationState().getLoggedInUser();
        
        // Only add admin interactions if the user has the required rights
        if (loggedInUser != null) {
            if (hasRight(loggedInUser, Right.CAN_DELETE_USER)) {
                addDeleteUserInteraction();
            }
            
            if (hasRight(loggedInUser, Right.CAN_CREATE_DRINK)) {
                addCreateDrinkOptionInteraction();
            }
        }
    }
    
    private boolean hasRight(User user, Right right) {
        return user.hasRight(right);
    }

    private void addLogoutInteraction() {
        LogoutInteraction interaction = new LogoutInteraction();
        interaction.onSuccess(event -> {
            thirstyCalc.logout();
            menuInteraction.stop();
        });
        menuInteraction.addInteraction("logout", "Logout a user", interaction);
    }

    private void addCheckBalanceInteraction() {
        CheckBalanceInteraction interaction = new CheckBalanceInteraction(thirstyCalc.getAccountOfLoggedInUser());
        interaction.onSuccess((money) -> {
            System.out.println("Your current balance is: " + money + ".");
        });
        menuInteraction.addInteraction("balance", "Shows your current balance.", interaction);
    }

    private void addCreateDrinkOptionInteraction() {
        CreateDrinkOptionInteraction interaction = new CreateDrinkOptionInteraction(thirstyCalc);
        interaction.onSuccess((drinkOption) -> {
            thirstyCalc.createNewDrinkOption(drinkOption);
            System.out.println("You created: " + drinkOption.getDrinkName().toString() + ", its color is: " + drinkOption.getColorName().toString() + ".");
        });
        menuInteraction.addInteraction("create-drink", "Create a new drink option.", interaction);
    }

    private void addDeleteUserInteraction() {
        DeleteUserInteraction interaction = new DeleteUserInteraction(thirstyCalc.getUserDatabase(), thirstyCalc.getAccountDatabase(), thirstyCalc);
        interaction.onSuccess((deleteUser) -> {
            thirstyCalc.deleteUser(deleteUser);
            System.out.println("You deleted the user: " + deleteUser.getUsername().toString() + ".");
        });
        menuInteraction.addInteraction("delete-user", "Delete a user", interaction);
    }
}
