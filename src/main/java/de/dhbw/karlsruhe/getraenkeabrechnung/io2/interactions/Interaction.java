package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

public interface Interaction<T> {

    String DEFAULT_PROMPT = "> ";

    void explain();

    T run();
}
