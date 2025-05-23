package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;

public class MainInteraction extends MenuInteraction
{

    private final ThirstyCalc thirstycalc;

    public MainInteraction(ThirstyCalc thirstycalc)
    {
        super();
        this.thirstycalc = thirstycalc;

        addExitInteraction();
        addRegisterInteraction();
        addLoginInteraction();
    }

    private void addExitInteraction()
    {
        ExitInteraction exitInteraction = new ExitInteraction();
        exitInteraction.onSuccess(event ->
        {
            this.onExit();
        });
        addInteraction("exit", "exits the program", exitInteraction);
    }

    private void addRegisterInteraction()
    {
        RegisterUserInteraction interaction = new RegisterUserInteraction(thirstycalc.getUserDatabase());
        interaction.onSuccess(thirstycalc::registerNewUser);
        addInteraction("register", "Register a new user", interaction);
    }

    private void addLoginInteraction()
    {
        LoginInteraction interaction = new LoginInteraction(thirstycalc.getUserDatabase());
        interaction.onSuccess(this::onLogin);
        addInteraction("login", "Login a user", interaction);
    }


    private void onLogin(User user)
    {
        thirstycalc.login(user);
        if (thirstycalc.getApplicationState().isLoggedIn())
        {
            System.out.println("Welcome " + user.getUsername() + "!");
            new LoggedInUserInteractionFactory(thirstycalc).build().run();
        }
    }

    private void onExit()
    {
        System.out.println("On exit");
        thirstycalc.save();
        stop();
    }
}
