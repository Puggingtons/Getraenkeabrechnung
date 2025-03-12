package io2;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.SelectInput;
import io2.mocks.InputReaderMock;
import io2.mocks.OutputWriterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectInputTest {
    private InputReaderMock readerMock;
    private OutputWriterMock writerMock;

    @BeforeEach
    public void setup() {
        readerMock = new InputReaderMock();
        writerMock = new OutputWriterMock();
    }

    @Test
    public void itPrintsOption() {
        String[] options = new String[]{"Prompt"};
        SelectInput input = new SelectInput(options);

        input.setReader(readerMock);
        input.setWriter(writerMock);

        input.prompt();

        assertEquals("[0] " + options[0] + "\n", writerMock.getOutput());
    }

    @Test
    public void itPrintsAllOptions() {
        String[] options = new String[]{"Prompt", "Prompt2", "Prompt3"};
        SelectInput input = new SelectInput(options);

        input.setReader(readerMock);
        input.setWriter(writerMock);

        input.prompt();

        String expectedOutput = "[0] " + options[0] + "\n[1] " + options[1] + "\n[2] " + options[2] + "\n";

        assertEquals(expectedOutput, writerMock.getOutput());
    }

//    todo: test unhappy paths
}
