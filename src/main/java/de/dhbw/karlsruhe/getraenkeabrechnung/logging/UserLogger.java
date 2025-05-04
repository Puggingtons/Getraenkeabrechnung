package de.dhbw.karlsruhe.getraenkeabrechnung.logging;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;

public class UserLogger implements Logger
{

    private User user;
    private final Logger logger;

    public UserLogger(User user, Logger logger)
    {
        this.user = user;
        this.logger = logger;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public void log(String message)
    {
        this.logger.log(String.format("[%s] %s", user.getUsername(), message));
    }

    @Override
    public Logger getInnerLogger()
    {
        return logger;
    }
}
