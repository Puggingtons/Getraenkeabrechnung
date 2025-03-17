package de.dhbw.karlsruhe.getraenkeabrechnung.io.writer;

import java.io.PrintStream;

public class OutputWriter implements Writer {
    private final PrintStream out;

    public OutputWriter() {
        this.out = System.out;
    }

    public OutputWriter(PrintStream out) {
        this.out = out;
    }

    public void writeLine(String line) {
        out.println(line);
    }

    public void write(String str) {
        out.print(str);
    }
}
