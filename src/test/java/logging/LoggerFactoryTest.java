package logging;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.Logger;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.LoggerFactory;
import logging.mocks.LogWriterMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerFactoryTest {

    @Test
    public void itLogsUserInDefaultUserLogger() {
        String msg = "test";
        User user = new User(new Username("dings"), new Password("password"));

        LogWriterMock mock = new LogWriterMock();
        LoggerFactory factory = new LoggerFactory(mock);
        Logger logger = factory.defaultUserLogger(user);

        logger.log(msg);

        assertTrue(mock.getLastLine().contains(user.getUsername().toString()));
        assertTrue(mock.getLastLine().contains(msg));
    }

    @Test
    public void itLogsUserAndClass() {
        String msg = "test";
        User user = new User(new Username("dings"), new Password("password"));

        LogWriterMock mock = new LogWriterMock();
        LoggerFactory factory = new LoggerFactory(mock);

        Logger logger = factory.addClassLogger(LoggerFactory.class).addUserLogger(user).build();

        logger.log(msg);

        assertTrue(mock.getLastLine().contains(user.getUsername().toString()));
        assertTrue(mock.getLastLine().contains(LoggerFactory.class.getSimpleName()));
        assertTrue(mock.getLastLine().contains(msg));

    }
}
