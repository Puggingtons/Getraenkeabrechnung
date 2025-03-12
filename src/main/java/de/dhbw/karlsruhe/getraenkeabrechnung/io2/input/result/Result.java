package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result;

public class Result<T> {

    private final T value;
    private final ResultState state;

    private Result(T value, ResultState state) {
        this.value = value;
        this.state = state;
    }

    public static <T> Result<T> some(T value) {
        return new Result<T>(value, ResultState.Value);
    }

    public static <T> Result<T> none() {
        return new Result<>(null, ResultState.Value);
    }

    public static <T> Result<T> help() {
        return new Result<>(null, ResultState.Help);
    }

    public T getValue() {
        return value;
    }

    public boolean hasValue() {
        return value != null;
    }

    public boolean isNone() {
        return state == ResultState.Value && value == null;
    }

    public boolean isHelp() {
        return state == ResultState.Help;
    }

    public boolean isAbort() {
        return state == ResultState.Abort;
    }


}
