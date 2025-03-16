package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions.Interaction;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions.MainInteraction;

public class Main {
    public static void main(String[] args) {
        Getraenkeabrechnung getraenkeabrechnung = new Getraenkeabrechnung();

        createMainInteraction(getraenkeabrechnung).run();
    }

    private static Interaction<Void> createMainInteraction(Getraenkeabrechnung getraenkeabrechnung) {
        return new MainInteraction(getraenkeabrechnung);
    }
}

