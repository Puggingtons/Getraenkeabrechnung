package io2.mocks;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.writer.Writer;

public class OutputWriterMock implements Writer {
    private String output;

    public OutputWriterMock() {
        output = "";
    }

    @Override
    public void writeLine(String line) {
        output = line;
    }

    @Override
    public void write(String str) {
        output = str;
    }

    public String getOutput() {
        return output;
    }
}
