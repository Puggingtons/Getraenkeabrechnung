package de.dhbw.karlsruhe.getraenkeabrechnung.io.cli;

public interface Cli {

    String DEFAULT_PROMPT_PREFIX = "> ";

    void setPromptPrefix(String prefix);

    void printMessage(String message);

    void printError(String message);

    String promptInput(String message);

    int promtIntInput(String message);
}