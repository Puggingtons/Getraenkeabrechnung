package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InteractionEventSource<T>
{

    private final List<Consumer<T>> onSuccessConsumers;
    private final List<Consumer<Void>> onFailureConsumers;

    public InteractionEventSource()
    {
        this.onSuccessConsumers = new ArrayList<>();
        this.onFailureConsumers = new ArrayList<>();
    }

    public void onSuccess(Consumer<T> onSuccess)
    {
        this.onSuccessConsumers.add(onSuccess);
    }

    protected void success(T t)
    {
        for (Consumer<T> onSuccess : onSuccessConsumers)
        {
            onSuccess.accept(t);
        }
    }

    public void onFailure(Consumer<Void> onFailure)
    {
        this.onFailureConsumers.add(onFailure);
    }

    protected void failure()
    {
        for (Consumer<Void> onFailure : onFailureConsumers)
        {
            onFailure.accept(null);
        }
    }
}
