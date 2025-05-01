package logging.mocks;

import de.dhbw.karlsruhe.getraenkeabrechnung.logging.logwriter.LogWriter;

public class LogWriterMock implements LogWriter {

    private String lastLine;

    public String getLastLine() {
        return lastLine;
    }

    @Override
    public void write(String message) {
        lastLine = message;
    }
}
