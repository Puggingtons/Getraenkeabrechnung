package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions.event;

import java.util.function.Consumer;

public class InteractionEventSource<T> {

    private Consumer<T> onSuccess;
    private Consumer<Void> onFailure;

    public InteractionEventSource() {
        this.onSuccess = null;
        this.onFailure = null;
    }

    public void onSuccess(Consumer<T> onSuccess) {
        this.onSuccess = onSuccess;
    }

    protected void success(T t) {
        if (onSuccess != null) {
            onSuccess.accept(t);
        }
    }

    public void onFailure(Consumer<Void> onFailure) {
        this.onFailure = onFailure;
    }

    protected void failure() {
        if (onFailure != null) {
            onFailure.accept(null);
        }
    }
}
