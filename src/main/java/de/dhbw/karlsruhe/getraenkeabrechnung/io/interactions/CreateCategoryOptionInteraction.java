package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.CategoryName;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.CategoryOption;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.DoubleInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class CreateCategoryOptionInteraction extends Interaction<CategoryOption> {

    private final ThirstyCalc thirstyCalc;

    private final StringInput colorNameInput;
    private final DoubleInput colorPriceInput;

    public CreateCategoryOptionInteraction(ThirstyCalc thirstyCalc) {

        this.thirstyCalc = thirstyCalc;

        colorNameInput = new StringInput("Category name (color): ");
        colorPriceInput = new DoubleInput("Category price: ");
    }

    @Override
    String usage() {
        return "Creates a new category option.";
    }

    @Override
    protected void execute() {
        String colorName = getValidInput(colorNameInput);
        double colorPrice = getValidInput(colorPriceInput);

        CategoryOption categoryOption = new CategoryOption(new CategoryName(colorName), colorPrice);

        if (thirstyCalc.categoryOptionExists(categoryOption)) {
            // If the category option already exists, we don't need to add it again
            System.out.println("Category with that color and that price already exists!");
            failure();
            return;
        }

        success(categoryOption);
    }

    private String getValidInput(StringInput input) {
        while (true) {
            Result<String> result = input.prompt();

            if (result.isHelp()) {
                explain();
                continue;
            }

            if (result.isNone()) {
                System.out.println("Invalid input!");
                continue;
            }

            return result.getValue();
        }
    }

    private double getValidInput(DoubleInput input) {
        while (true) {
            Result<Double> result = input.prompt();

            if (result.isHelp()) {
                explain();
                continue;
            }

            if (result.isNone()) {
                System.out.println("Invalid input!");
                continue;
            }

            return result.getValue();
        }
    }
    
}
