package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions.event.InteractionEventSource;

public abstract class Interaction<T> extends InteractionEventSource<T>
{

    private boolean run;

    /**
     * Stops onSuccess and onFailure.
     */
    public Interaction()
    {
        run = false;

        onSuccess(event ->
        {
            stop();
        });
        onFailure(event ->
        {
            stop();
        });
    }

    static final String DEFAULT_PROMPT = "> ";

    public void explain()
    {
        System.out.println(usage());
    }

    abstract String usage();

    public void run()
    {
        run = true;

        while (run)
        {
            execute();
        }
    }

    protected abstract void execute();

    protected void stop()
    {
        run = false;
    }
}
