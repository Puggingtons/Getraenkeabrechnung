package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

public class LogoutInteraction extends Interaction<Void> {
    @Override
    String usage() {
        return "You're now logged out";
    }

    @Override
    public void execute() {
        // todo check if user is logged in
        success(null);
    }
}
