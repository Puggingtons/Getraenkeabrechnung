package io.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.BooleanInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;
import io.mocks.InputReaderMock;
import io.mocks.OutputWriterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanInputTest {
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

        BooleanInput input = new BooleanInput(prompt);
        input.setReader(readerMock);
        input.setWriter(writerMock);

        input.prompt();

        assertEquals(prompt, writerMock.getOutput());
    }

    @Test
    public void itReturnsTrue1() {
        BooleanInput input = new BooleanInput("");
        input.setReader(readerMock);
        input.setWriter(writerMock);
        readerMock.setNextInput("y");

        Result<Boolean> res = input.prompt();

        assertEquals(true, res.getValue());
    }

    @Test
    public void itReturnsTrue2() {
        BooleanInput input = new BooleanInput("");
        input.setReader(readerMock);
        input.setWriter(writerMock);
        readerMock.setNextInput("yes");

        Result<Boolean> res = input.prompt();

        assertEquals(true, res.getValue());
    }

    @Test
    public void itReturnsFalse1() {
        BooleanInput input = new BooleanInput("");
        input.setReader(readerMock);
        input.setWriter(writerMock);
        readerMock.setNextInput("n");

        Result<Boolean> res = input.prompt();

        assertEquals(false, res.getValue());
    }

    @Test
    public void itReturnsFalse2() {
        BooleanInput input = new BooleanInput("");
        input.setReader(readerMock);
        input.setWriter(writerMock);
        readerMock.setNextInput("no");

        Result<Boolean> res = input.prompt();

        assertEquals(false, res.getValue());
    }

    @Test
    public void itReturnsHelp() {
        BooleanInput input = new BooleanInput("");
        input.setReader(readerMock);
        input.setWriter(writerMock);
        readerMock.setNextInput("help");

        Result<Boolean> res = input.prompt();

        assertTrue(res.isHelp());
    }

    @Test
    public void itReturnsNone1() {
        BooleanInput input = new BooleanInput("");
        input.setReader(readerMock);
        input.setWriter(writerMock);
        readerMock.setNextInput("yees");

        Result<Boolean> res = input.prompt();

        assertTrue(res.isNone());
    }

    @Test
    public void itReturnsNone2() {
        BooleanInput input = new BooleanInput("");
        input.setReader(readerMock);
        input.setWriter(writerMock);
        readerMock.setNextInput("noo");

        Result<Boolean> res = input.prompt();

        assertTrue(res.isNone());
    }
}
