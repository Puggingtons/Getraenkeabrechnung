package de.dhbw.karlsruhe.getraenkeabrechnung.logging.logwriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogWriter implements LogWriter
{

    private final File file;

    public FileLogWriter(File file) throws IOException
    {
        if (!file.exists())
        {
            file.createNewFile();
        }

        this.file = file;
    }

    @Override
    public void write(String message)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)))
        {
            bw.write(message);
            bw.newLine();
        } catch (IOException e)
        {
            System.err.println("Could not write log to file! Log: \"" + message + "\".");
        }
    }
}
