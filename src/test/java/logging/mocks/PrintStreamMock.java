package logging.mocks;

import java.io.PrintStream;

public class PrintStreamMock extends PrintStream
{

    private String lastLine;

    public String getLastLine()
    {
        return lastLine;
    }

    public PrintStreamMock()
    {
        super(System.out);
    }

    @Override
    public void println(String line)
    {
        this.lastLine = line;
    }
}
