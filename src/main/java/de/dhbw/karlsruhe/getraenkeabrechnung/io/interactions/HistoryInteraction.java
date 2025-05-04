package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.texts.History;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.texts.Language;

public class HistoryInteraction extends MenuInteraction
{

    private final History history;


    public HistoryInteraction()
    {
        history = new History();

        this.addInteraction("de", "Die Geschichte von ThirstyCalc auf deutsch", new Interaction<Void>()
        {

            @Override
            String usage()
            {
                return "Die Geschichte von ThirstyCalc auf deutsch";
            }

            @Override
            protected void execute()
            {
                history.printHistory(Language.DE);
                success(null);
            }
        });

        this.addInteraction("en", "The history of ThirstyCalc in english", new Interaction<Void>()
        {
            @Override
            String usage()
            {
                return "The history of ThirstyCalc in english";
            }

            @Override
            protected void execute()
            {
                history.printHistory(Language.EN);
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
