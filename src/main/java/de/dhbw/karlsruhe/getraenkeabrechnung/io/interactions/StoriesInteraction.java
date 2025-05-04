package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

public class StoriesInteraction extends MenuInteraction
{
    public StoriesInteraction() {
        this.addInteraction("history", "the history of ThirstyCalc", new HistoryInteraction());

        this.addInteraction("return", "go back", new Interaction<Void>() {
            @Override
            String usage()
            {
                return "go back";
            }

            @Override
            protected void execute()
            {
                stopStories();
                success(null);
            }
        });
    }

    private void stopStories()
    {
        stop();
    }
}
