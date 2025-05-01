package logging;

import de.dhbw.karlsruhe.getraenkeabrechnung.logging.LogWriterLoggerAdapter;
import logging.mocks.LogWriterMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogWriterLoggerAdapterTest {

    @Test
    public void itAdapts() {
        String msg = "test";

        LogWriterMock mock = new LogWriterMock();
        LogWriterLoggerAdapter adapter = new LogWriterLoggerAdapter(mock);

        adapter.log(msg);

        assertEquals(msg, mock.getLastLine());
    }
}
