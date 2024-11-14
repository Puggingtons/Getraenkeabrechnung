package de.dhbw.karlsruhe.getraenkeabrechnung.io.cli;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class StdCli implements Cli {

    private Scanner scan;
    private PrintStream out;

    private String promptPrefix;

    public StdCli(PrintStream out, InputStream in) {
        out.println("Cli working");

        scan = new Scanner(in);
        this.out = out;

        this.promptPrefix = DEFAULT_PROMPT_PREFIX;
    }

    @Override
    public void setPromptPrefix(String prefix) {
        this.promptPrefix = prefix;
    }

    @Override
    public void printMessage(String message) {
        this.out.print(message);
    }

    @Override
    public void printError(String message) {
        this.out.print("Error: " + message);
    }

    @Override
    public String promptInput(String message) {
        printMessage(message + "\n" + this.promptPrefix);

        return scan.nextLine();
    }

    @Override
    public int promtIntInput(String message) {
//        TODO: handle Exception
        return Integer.parseInt(promptInput(message));
    }
}
