package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.cli.StdCli;

public class Main {
    public static void main(String[] args) {
        StdCli cli = new StdCli(System.out, System.in);


        cli.print("Hallo Welt :)");
    }
}
