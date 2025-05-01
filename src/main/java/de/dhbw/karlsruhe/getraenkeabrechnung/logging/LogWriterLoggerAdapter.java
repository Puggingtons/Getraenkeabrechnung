package de.dhbw.karlsruhe.getraenkeabrechnung.logging;

import de.dhbw.karlsruhe.getraenkeabrechnung.logging.logwriter.LogWriter;

public class LogWriterLoggerAdapter implements Logger {

    private final LogWriter logWriter;

    public LogWriterLoggerAdapter(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    @Override
    public void log(String message) {
        this.logWriter.write(message);
    }

    @Override
    public Logger getInnerLogger() {
        return this;
    }
}
