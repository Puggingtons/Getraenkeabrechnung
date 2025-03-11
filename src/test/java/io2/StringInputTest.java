package io2;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.StringInput;
import io2.mocks.InputReaderMock;
import io2.mocks.OutputWriterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringInputTest {

    private InputReaderMock readerMock;
    private OutputWriterMock writerMock;

    @BeforeEach
    public void setup() {
        readerMock = new InputReaderMock();
        writerMock = new OutputWriterMock();
    }

    @Test
    public void itPrintsPrompt() {
        String prompt = "Prompt";
        StringInput input = new StringInput(prompt);

        input.setReader(readerMock);
        input.setWriter(writerMock);

        input.prompt();

        assertEquals(prompt, writerMock.getOutput());
    }

    @Test
    public void itReturnsInput() {
        String in = "input";

        StringInput input = new StringInput("");

        input.setReader(readerMock);
        input.setWriter(writerMock);

        readerMock.setNextInput(in);

        String res = input.prompt();

        assertEquals(in, res);
    }
}
