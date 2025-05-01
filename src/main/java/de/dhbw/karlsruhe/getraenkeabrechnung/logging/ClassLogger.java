package de.dhbw.karlsruhe.getraenkeabrechnung.logging;

public class ClassLogger implements Logger {

    private final Class<?> clazz;
    private final Logger logger;

    public ClassLogger(Class<?> clazz, Logger logger) {
        this.clazz = clazz;
        this.logger = logger;
    }

    @Override
    public void log(String message) {
        this.logger.log(String.format("[%s] %s", this.clazz.getSimpleName(), message));
    }
}
