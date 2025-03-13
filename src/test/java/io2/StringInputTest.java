package io2;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.StringInput;
import io2.mocks.InputReaderMock;
import io2.mocks.OutputWriterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        Result<String> res = input.prompt();

        assertTrue(res.hasValue());
        assertEquals(in, res.getValue());
    }

    @Test
    public void itReturnsEmptyInput() {
        String in = "";

        StringInput input = new StringInput("");

        input.setReader(readerMock);
        input.setWriter(writerMock);

        readerMock.setNextInput(in);

        Result<String> res = input.prompt();

        assertTrue(res.isNone());
    }

//    todo: test unhappy paths
}
