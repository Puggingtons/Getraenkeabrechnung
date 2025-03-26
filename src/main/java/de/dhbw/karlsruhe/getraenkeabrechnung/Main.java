package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions.Interaction;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions.MainInteraction;

public class Main {
    public static void main(String[] args) {
        ThirstyCalc thirstycalc = new ThirstyCalc();

        thirstycalc.load();

        createMainInteraction(thirstycalc).run();
    }

    private static Interaction<Void> createMainInteraction(ThirstyCalc thirstycalc) {
        return new MainInteraction(thirstycalc);
    }
}

