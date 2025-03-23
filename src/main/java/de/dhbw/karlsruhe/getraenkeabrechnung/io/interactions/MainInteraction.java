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
    }

    private void addExitInteraction() {
        addInteraction("exit", "exits the program", null);
    }

    private void addRegisterInteraction() {
        CreateUserInteraction interaction = new CreateUserInteraction();
        interaction.onSuccess(thirstycalc::createNewUser);
        addInteraction("register", "Register a new user", interaction);
    }

    private void addLoginInteraction() {
        LoginInteraction interaction = new LoginInteraction();
        interaction.onSuccess(this::onLogin);
        addInteraction("login", "Login a user", interaction);
    }

    private void onLogin(User user) {
        thirstycalc.login(user);
        if (thirstycalc.getApplicationState().isLoggedIn()) {
            new LoggedInUserInteractionFactory(thirstycalc).build().run();
        }
    }
}
