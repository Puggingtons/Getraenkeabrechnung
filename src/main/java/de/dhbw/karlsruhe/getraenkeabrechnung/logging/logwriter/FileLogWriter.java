package de.dhbw.karlsruhe.getraenkeabrechnung.logging.logwriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogWriter implements LogWriter {

    private final FileWriter writer;

    public FileLogWriter(File file) throws IOException {
        writer = new FileWriter(file, true);
    }

    @Override
    public void write(String message) {
        try {
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(message);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.err.println("Could not write log to file! Log: \"" + message + "\".");
        }

    }
}
