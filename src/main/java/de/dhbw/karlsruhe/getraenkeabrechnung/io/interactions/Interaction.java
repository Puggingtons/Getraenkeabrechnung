package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

public interface Interaction<T> {

    String DEFAULT_PROMPT = "> ";

    void explain();

    T run();
}
