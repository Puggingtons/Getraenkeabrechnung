package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

public class ExitInteraction extends Interaction<Void> {
    @Override
    void explain() {

    }

    @Override
    protected void execute() {
        success(null);
    }
}
