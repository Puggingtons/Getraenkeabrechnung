package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

public class ExitInteraction extends Interaction<Void>
{
    @Override
    String usage()
    {
        return "Exits the application.";
    }

    @Override
    protected void execute()
    {
        success(null);
    }
}
