package io2;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.NumberInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;
import io2.mocks.InputReaderMock;
import io2.mocks.OutputWriterMock;
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

//    todo: test unhappy paths
}
