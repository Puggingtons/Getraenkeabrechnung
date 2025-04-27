package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;

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

    }

    private void addLogoutInteraction() {
        LogoutInteraction interaction = new LogoutInteraction();
        interaction.onSuccess((_) -> {
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
}
