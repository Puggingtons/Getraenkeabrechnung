package io;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.NumberInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;
import io.mocks.InputReaderMock;
import io.mocks.OutputWriterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberInputTest {
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
        NumberInput input = new NumberInput(prompt);

        input.setReader(readerMock);
        input.setWriter(writerMock);

        input.prompt();

        assertEquals(prompt, writerMock.getOutput());
    }

    @Test
    public void itReturnsNumber() {
        int in = 1;
        NumberInput input = new NumberInput("");

        input.setReader(readerMock);
        input.setWriter(writerMock);

        readerMock.setNextInput(String.valueOf(in));

        Result<Integer> res = input.prompt();

        assertTrue(readerMock.hasBeenRead());
        assertTrue(res.hasValue());
        assertEquals(in, res.getValue());
    }

    @Test
    public void itReturnsHelp() {
        NumberInput input = new NumberInput("");

        input.setReader(readerMock);
        input.setWriter(writerMock);

        readerMock.setNextInput("help");

        Result<Integer> res = input.prompt();

        assertTrue(readerMock.hasBeenRead());
        assertTrue(res.isHelp());
    }

    @Test
    public void itReturnsNoValue() {
        NumberInput input = new NumberInput("");

        input.setReader(readerMock);
        input.setWriter(writerMock);

        readerMock.setNextInput("asdasd");

        Result<Integer> res = input.prompt();

        assertTrue(readerMock.hasBeenRead());
        assertTrue(res.isNone());
    }

//    todo: test unhappy paths
}
