package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

public class LogoutInteraction extends Interaction<Void> {
    @Override
    public void explain() {
        System.out.println("You're now logged out");
    }

    @Override
    public Void run() {
        // todo check if user is logged in
        success(null);
        return null;
    }
}
