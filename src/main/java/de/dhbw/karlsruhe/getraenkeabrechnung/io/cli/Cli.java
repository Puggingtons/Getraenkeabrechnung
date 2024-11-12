package de.dhbw.karlsruhe.getraenkeabrechnung.io.cli;

import java.util.Optional;

public interface Cli {

    void print(String msg);

    void printLine(String msg);

    Optional<String> getInput();

    Optional<String> getInput(String msg);

    Optional<Integer> getNumberInput();

    Optional<Integer> getNumberInput(String msg);
}