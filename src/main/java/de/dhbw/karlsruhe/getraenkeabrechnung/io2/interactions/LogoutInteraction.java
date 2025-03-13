package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

public class LogoutInteraction implements Interaction<Void> {
    @Override
    public void explain() {
        System.out.println("You're now logged out");
    }

    @Override
    public Void run() {
        return null;
    }
}
