package de.dhbw.karlsruhe.getraenkeabrechnung.logging.logwriter;

import java.io.PrintStream;

public class PrintStreamLogWriter implements LogWriter {

    private final PrintStream printStream;

    public PrintStreamLogWriter(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void write(String message) {
        this.printStream.println(message);
    }
}
