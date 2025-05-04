package logging.logwriter;

import de.dhbw.karlsruhe.getraenkeabrechnung.logging.logwriter.PrintStreamLogWriter;
import logging.mocks.PrintStreamMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintStreamLogWriterTest
{

    @Test
    public void itWrites()
    {
        String msg = "test";

        PrintStreamMock mock = new PrintStreamMock();
        PrintStreamLogWriter logWriter = new PrintStreamLogWriter(mock);

        logWriter.write(msg);

        assertEquals(msg, mock.getLastLine());
    }
}
