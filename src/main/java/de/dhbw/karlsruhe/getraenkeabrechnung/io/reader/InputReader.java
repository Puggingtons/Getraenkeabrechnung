package de.dhbw.karlsruhe.getraenkeabrechnung.io.reader;

import java.io.InputStream;
import java.util.Scanner;

public class InputReader implements Reader {
    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public InputReader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String readLine() {
        return this.scanner.nextLine();
    }
}
