package de.dhbw.karlsruhe.getraenkeabrechnung.logging;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.logwriter.FileLogWriter;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.logwriter.LogWriter;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.logwriter.PrintStreamLogWriter;

import java.io.File;
import java.io.IOException;

public class LoggerFactory {
    private Logger logger;

    public LoggerFactory() {
        setBaseLogger();
    }

    private void setBaseLogger() {
        try {
            logger = new LogWriterLoggerAdapter(new FileLogWriter(new File("log.txt")));
        } catch (IOException e) {
            logger = new LogWriterLoggerAdapter(new PrintStreamLogWriter(System.out));
        }
    }

    public LoggerFactory(LogWriter logWriter) {
        this.logger = new LogWriterLoggerAdapter(logWriter);
    }

    public LoggerFactory(Logger logger) {
        this.logger = logger;
    }

    public LoggerFactory addUserLogger(User user) {
        this.logger = new UserLogger(user, this.logger);

        return this;
    }

    public LoggerFactory addTimeLogger() {
        this.logger = new TimeLogger(this.logger);

        return this;
    }

    public LoggerFactory addClassLogger(Class<?> clazz) {
        this.logger = new ClassLogger(clazz, this.logger);

        return this;
    }

    public Logger build() {
        return logger;
    }

    public Logger defaultUserLogger(User user) {
        return new TimeLogger(new UserLogger(user, this.logger));
    }

    public Logger defaultTimeLogger() {
        return new TimeLogger(this.logger);
    }
}
