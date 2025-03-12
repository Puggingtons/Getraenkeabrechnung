package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.NumberInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;

import java.util.ArrayList;
import java.util.List;

public class SelectInteraction implements Interaction {

    private final List<String> options;

    public SelectInteraction() {
        this.options = new ArrayList<>();
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    public void explain() {
        for (int i = 0; i < options.size(); i++) {
            System.out.println("[" + i + "] " + options.get(i));
        }
    }

    public void run() {
        NumberInput numberInput = new NumberInput("Enter a number between 0 and " + (options.size() - 1));

        while (true) {
            Result<Integer> result = numberInput.prompt();

            if (result.isNone()) {
                System.out.println("Invalid input!");
                return; // todo
            }

            if(result.isHelp()) {
                explain();
                continue;
            }

            int number = result.getValue();

            if (number < 0 || number >= options.size()) {
                System.out.println("Number out of bounds!");
                continue;
            }

            System.out.println("[ " + number + " ] " + options.get(number));
            return;
        }


    }
}