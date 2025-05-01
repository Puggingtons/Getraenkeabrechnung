package logging;

import de.dhbw.karlsruhe.getraenkeabrechnung.logging.ClassLogger;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.LogWriterLoggerAdapter;
import logging.mocks.LogWriterMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassLoggerTest {

    @Test
    public void itLogsClass() {
        String msg = "test";

        LogWriterMock mock = new LogWriterMock();
        ClassLogger logger = new ClassLogger(ClassLogger.class, new LogWriterLoggerAdapter(mock));

        logger.log(msg);

        assertTrue(mock.getLastLine().contains(ClassLogger.class.getSimpleName()));
        assertTrue(mock.getLastLine().contains(msg));
    }

}
