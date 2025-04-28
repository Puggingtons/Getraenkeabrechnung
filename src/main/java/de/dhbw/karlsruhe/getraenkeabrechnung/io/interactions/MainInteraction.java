package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;

public class MainInteraction extends MenuInteraction {

    private final ThirstyCalc thirstycalc;

    public MainInteraction(ThirstyCalc thirstycalc) {
        super();
        this.thirstycalc = thirstycalc;

        addExitInteraction();
        addRegisterInteraction();
        addLoginInteraction();
        addDeleteUserInteraction();
    }

    private void addExitInteraction() {
        ExitInteraction exitInteraction = new ExitInteraction();
        exitInteraction.onSuccess(event -> { this.onExit(); });
        addInteraction("exit", "exits the program", exitInteraction);
    }

    private void addRegisterInteraction() {
        CreateUserInteraction interaction = new CreateUserInteraction(thirstycalc.getUserDatabase());
        interaction.onSuccess(thirstycalc::createNewUser);
        addInteraction("register", "Register a new user", interaction);
    }

    private void addLoginInteraction() {
        LoginInteraction interaction = new LoginInteraction(thirstycalc.getUserDatabase());
        interaction.onSuccess(this::onLogin);
        addInteraction("login", "Login a user", interaction);
    }

    private void addDeleteUserInteraction() {
        DeleteUserInteraction interaction = new DeleteUserInteraction(thirstycalc.getUserDatabase(), thirstycalc.getAccountDatabase());
        interaction.onSuccess(thirstycalc::deleteUser);
        addInteraction("delete", "Delete a user", interaction);
    }

    private void onLogin(User user) {
        thirstycalc.login(user);
        if (thirstycalc.getApplicationState().isLoggedIn()) {
            new LoggedInUserInteractionFactory(thirstycalc).build().run();
        }
    }

    private void onExit() {
        System.out.println("On exit");
        thirstycalc.save();
        stop();
    }
}
