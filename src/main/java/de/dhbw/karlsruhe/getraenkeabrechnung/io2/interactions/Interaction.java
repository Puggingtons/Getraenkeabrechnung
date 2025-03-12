package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

public interface Interaction {

    String DEFAULT_PROMPT = "> ";

    void explain();

    void run();
}
