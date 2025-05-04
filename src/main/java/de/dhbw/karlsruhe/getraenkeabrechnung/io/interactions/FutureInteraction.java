package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.texts.Future;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.texts.Language;

public class FutureInteraction extends MenuInteraction
{

    private final Future future;


    public FutureInteraction()
    {
        future = new Future();

        this.addInteraction("de", "Die Zukunft von ThirstyCalc auf deutsch", new Interaction<Void>()
        {

            @Override
            String usage()
            {
                return "Die Zukunft von ThirstyCalc auf deutsch";
            }

            @Override
            protected void execute()
            {
                future.printFuture(Language.DE);
                success(null);
            }
        });

        this.addInteraction("en", "The future of ThirstyCalc in english", new Interaction<Void>()
        {
            @Override
            String usage()
            {
                return "The future of ThirstyCalc in english";
            }

            @Override
            protected void execute()
            {
                future.printFuture(Language.EN);
                success(null);
            }
        });

        this.addInteraction("return", "go back", new Interaction<Void>() {
            @Override
            String usage()
            {
                return "go back";
            }

            @Override
            protected void execute()
            {
                stopHistory();
                success(null);
            }
        });
    }

    private void stopHistory() {
        success(null);
        stop();
    }
}
