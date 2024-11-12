package de.dhbw.karlsruhe.getraenkeabrechnung.io.cli;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class StdCli implements Cli {

    private Scanner scan;

    public StdCli(PrintStream out, InputStream in) {
        out.println("Cli working");

        scan = new Scanner(in);
    }

    @Override
    public void print(String msg) {
        System.out.print(msg);
    }

    @Override
    public void printLine(String msg) {
        System.out.println(msg);
    }

    @Override
    public Optional<String> getInput() {
        try {
            return Optional.ofNullable(scan.nextLine());
        } catch (NoSuchElementException | IllegalStateException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> getInput(String msg) {
        this.print(msg);
        return this.getInput();
    }

    @Override
    public Optional<Integer> getNumberInput() {
        Optional<String> input = this.getInput();
        if (input.isPresent()) {
            try {
                return Optional.of(Integer.parseInt(input.get()));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Integer> getNumberInput(String msg) {
        this.print(msg);
        return this.getNumberInput();
    }
}
