package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions.event.InteractionEventSource;

public class LogoutInteraction extends InteractionEventSource<Void> implements Interaction<Void> {
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
