package logging;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.UserLogger;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.LogWriterLoggerAdapter;
import logging.mocks.LogWriterMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserLoggerTest
{

    @Test
    public void itLogsUsername()
    {
        String msg = "test";
        User user = new User(new Username("Dings"), new Password("bums"));

        LogWriterMock mock = new LogWriterMock();
        UserLogger logger = new UserLogger(user, new LogWriterLoggerAdapter(mock));

        logger.log(msg);

        assertTrue(mock.getLastLine().contains(user.getUsername().toString()));
        assertTrue(mock.getLastLine().contains(msg));
    }
}
