package de.dhbw.karlsruhe.getraenkeabrechnung.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeLogger implements Logger
{
    private final Logger logger;
    private final DateTimeFormatter dateTimeFormatter;

    private LocalDateTime time;

    public TimeLogger(Logger logger)
    {
        this.logger = logger;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public void setTimeOnce(LocalDateTime time)
    {
        this.time = time;
    }

    private LocalDateTime getTime()
    {
        LocalDateTime ret = time == null ? LocalDateTime.now() : time;

        time = null;

        return ret;
    }

    @Override
    public void log(String message)
    {
        logger.log(String.format("[%s] %s", dateTimeFormatter.format(getTime()), message));
    }


    @Override
    public Logger getInnerLogger()
    {
        return logger;
    }
}
