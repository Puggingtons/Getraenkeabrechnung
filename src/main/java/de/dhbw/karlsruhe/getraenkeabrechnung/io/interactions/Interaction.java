package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions.event.InteractionEventSource;

public abstract class Interaction<T> extends InteractionEventSource<T> {

    static final String DEFAULT_PROMPT = "> ";

    abstract void explain();

    public abstract T run();
}
