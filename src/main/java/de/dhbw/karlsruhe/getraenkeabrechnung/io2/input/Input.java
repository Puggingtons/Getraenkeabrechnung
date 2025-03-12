package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.reader.InputReader;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.reader.Reader;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.writer.OutputWriter;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.writer.Writer;

abstract class Input {
    private Reader reader;
    private Writer writer;

    protected Input() {
        this.reader = new InputReader();
        this.writer = new OutputWriter();
    }

    protected Input(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    protected String readInput() {
        return this.reader.readLine();
    }

    protected void print(String str) {
        writer.write(str);
    }

    protected void println(String str) {
        writer.writeLine(str);
    }

    abstract String prompt();
}
