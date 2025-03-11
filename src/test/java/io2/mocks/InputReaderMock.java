package io2.mocks;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.Reader;

public class InputReaderMock implements Reader {

    private String input;
    private boolean hasBeenRead;

    public InputReaderMock() {
        this.input = "";
        this.hasBeenRead = false;
    }

    public void setNextInput(String input) {
        this.input = input;
    }

    @Override
    public String readLine() {
        this.hasBeenRead = true;
        String ret = input;

        this.input = null;

        return ret;
    }

    public boolean hasBeenRead() {
        return hasBeenRead;
    }
}
