package logging;

import de.dhbw.karlsruhe.getraenkeabrechnung.logging.TimeLogger;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.LogWriterLoggerAdapter;
import logging.mocks.LogWriterMock;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeLoggerTest {

    @Test
    public void itLogsTime() {
        LocalDateTime now = LocalDateTime.now();
        String dateString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
        String msg = "test";

        LogWriterMock mock = new LogWriterMock();
        TimeLogger logger = new TimeLogger(new LogWriterLoggerAdapter(mock));
        logger.setTimeOnce(now);

        logger.log(msg);

        assertTrue(mock.getLastLine().contains(dateString));
        assertTrue(mock.getLastLine().contains(msg));
    }
}
