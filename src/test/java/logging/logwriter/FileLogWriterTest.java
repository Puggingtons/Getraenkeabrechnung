package logging.logwriter;

import de.dhbw.karlsruhe.getraenkeabrechnung.logging.logwriter.FileLogWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileLogWriterTest {

    @Test
    public void itWrites(@TempDir Path tempDir) {
        File tmpFile = tempDir.resolve("log.txt").toFile();
        String msg = "test";

        try {
            FileLogWriter fileLogWriter = new FileLogWriter(tmpFile);
            fileLogWriter.write(msg);

            assertEquals(msg + "\n", Files.readString(tmpFile.toPath()));

        } catch (IOException e) {
            Assertions.fail("Could not create file log writer: " + e.getMessage());
        }
    }

    @Test
    public void itAppends(@TempDir Path tempDir) {
        File tmpFile = tempDir.resolve("log.txt").toFile();

        String oldMsg = "old\n";
        String msg = "new";

        try {
            Files.writeString(tmpFile.toPath(), oldMsg);

            FileLogWriter fileLogWriter = new FileLogWriter(tmpFile);
            fileLogWriter.write(msg);

            assertEquals(oldMsg + msg + "\n", Files.readString(tmpFile.toPath()));
        } catch (IOException e) {
            Assertions.fail("Could not create file log writer: " + e.getMessage());
        }
    }
}
