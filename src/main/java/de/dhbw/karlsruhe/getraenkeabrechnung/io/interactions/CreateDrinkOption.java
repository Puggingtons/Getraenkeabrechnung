package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;
import de.dhbw.karlsruhe.getraenkeabrechnung.ColorName;
import de.dhbw.karlsruhe.getraenkeabrechnung.DrinkOption;
import de.dhbw.karlsruhe.getraenkeabrechnung.DrinkName;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class CreateDrinkOption extends Interaction<DrinkOption> {

    private final StringInput drinkNameInput;
    private final StringInput colorNameInput;

    public CreateDrinkOption() {
        drinkNameInput = new StringInput("Drinkname: ");
        colorNameInput = new StringInput("Drinkcolor: ");
    }

    @Override
    String usage() {
        return "Creates a new drink option.";
    }

    @Override
    protected void execute() {
        String drinkName = getValidInput(drinkNameInput);
        String colorName = getValidInput(colorNameInput);

        // todo: check drink database
        if (drinkName.equals("yellow")) {
            System.out.println("Category already exists!");
            failure();
            return;
        }

        DrinkOption drinkOption = new DrinkOption(new DrinkName(drinkName), new ColorName(colorName));

        success(drinkOption);
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

}

