package de.dhbw.karlsruhe.getraenkeabrechnung.io.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.reader.InputReader;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.reader.Reader;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.writer.OutputWriter;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.writer.Writer;

abstract class Input<T>
{
    private final String prompt;
    private Reader reader;
    private Writer writer;

    protected Input()
    {
        this("");
    }

    protected Input(String prompt)
    {
        this(new InputReader(), new OutputWriter(), prompt);
    }

    protected Input(InputReader reader, OutputWriter writer, String prompt)
    {
        this.reader = reader;
        this.writer = writer;
        this.prompt = prompt;
    }

    public void setReader(Reader reader)
    {
        this.reader = reader;
    }

    public void setWriter(Writer writer)
    {
        this.writer = writer;
    }

    protected String readInput()
    {
        return this.reader.readLine();
    }

    protected boolean isHelp(String str)
    {
        return str.equals("help");
    }

    protected void print(String str)
    {
        writer.write(str);
    }

    protected void println(String str)
    {
        writer.writeLine(str);
    }

    public Result<T> prompt()
    {
        print(prompt);

        String in = readInput();

        if (isHelp(in))
        {
            return Result.help();
        }

        if (in.isBlank())
        {
            return Result.none();
        }

        return getResult(in);
    }

    abstract Result<T> getResult(String input);
}
