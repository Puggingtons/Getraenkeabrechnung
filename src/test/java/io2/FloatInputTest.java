package io2;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.FloatInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;
import io2.mocks.InputReaderMock;
import io2.mocks.OutputWriterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FloatInputTest {
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
        FloatInput input = new FloatInput(prompt);

        input.setReader(readerMock);
        input.setWriter(writerMock);

        input.prompt();

        assertEquals(prompt, writerMock.getOutput());
    }

    @Test
    public void itReturnsNumber() {
        float in = 1.0f;
        FloatInput input = new FloatInput("");

        input.setReader(readerMock);
        input.setWriter(writerMock);

        readerMock.setNextInput(String.valueOf(in));

        Result<Float> res = input.prompt();

        assertTrue(readerMock.hasBeenRead());
        assertTrue(res.hasValue());
        assertEquals(in, res.getValue());
    }

    @Test
    public void itReturnsHelp() {
        FloatInput input = new FloatInput("");

        input.setReader(readerMock);
        input.setWriter(writerMock);

        readerMock.setNextInput("help");

        Result<Float> res = input.prompt();

        assertTrue(readerMock.hasBeenRead());
        assertTrue(res.isHelp());
    }

    @Test
    public void itReturnsNoValue() {
        FloatInput input = new FloatInput("");

        input.setReader(readerMock);
        input.setWriter(writerMock);

        readerMock.setNextInput("asdasd");

        Result<Float> res = input.prompt();

        assertTrue(readerMock.hasBeenRead());
        assertTrue(res.isNone());
    }

//    todo: test unhappy paths
}

