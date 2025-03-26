package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions.event.InteractionEventSource;

public abstract class Interaction<T> extends InteractionEventSource<T> {

    private boolean run;

    /**
     * Stops onSuccess and onFailure.
     */
    public Interaction() {
        run = false;

        onSuccess((_) -> {stop();});
        onFailure((_) -> {stop();});
    }

    static final String DEFAULT_PROMPT = "> ";

    abstract void explain();

    public void run() {
        run = true;

        while (run) {
            execute();
        }
    }

    protected abstract void execute();

    protected void stop() {
        run = false;
    }
}
