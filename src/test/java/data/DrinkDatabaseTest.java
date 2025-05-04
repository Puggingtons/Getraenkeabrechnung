package data;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.google.gson.Gson;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.ColorName;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.DrinkName;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.DrinkOption;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.DrinkDatabase;

public class DrinkDatabaseTest
{

    @Test
    public void itSavesDrinkOption(@TempDir Path tempDir)
    {
        DrinkOption[] drinkOptions = {new DrinkOption(new DrinkName("beer"), new ColorName("yellow"))};

        DrinkDatabase drinkDatabase = new DrinkDatabase();
        for (DrinkOption drinkOption : drinkOptions)
        {
            drinkDatabase.addDrinkOption(drinkOption);
        }

        Path filePath = tempDir.resolve("drinks.json");
        Gson gson = new Gson();

        assertDoesNotThrow(() ->
        {
            drinkDatabase.save(filePath);
            assertEquals(Files.readString(filePath), gson.toJson(drinkOptions));
        });
    }

    @Test
    public void itLoadsDrinkOptions(@TempDir Path tempDir)
    {
        DrinkOption drinkOption = new DrinkOption(new DrinkName("beer"), new ColorName("yellow"));
        DrinkOption drinkOption2 = new DrinkOption(new DrinkName("water"), new ColorName("blue"));

        DrinkDatabase drinkDatabase = new DrinkDatabase();
        drinkDatabase.addDrinkOption(drinkOption);
        drinkDatabase.addDrinkOption(drinkOption2);

        Path filePath = tempDir.resolve("drinks.json");

        int initLength = drinkDatabase.getDrinkOptions().length;

        assertDoesNotThrow(() ->
        {
            drinkDatabase.save(filePath);
            drinkDatabase.load(filePath);

            assertEquals(initLength, drinkDatabase.getDrinkOptions().length);
        });
    }
}
