package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

import com.google.gson.reflect.TypeToken;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.Savable;

import java.util.List;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class DrinkDatabase {
    private static Savable<List<DrinkOption>> drinkOptionList;

    public DrinkDatabase() {
        this.drinkOptionList = new Savable<>( new ArrayList<>());
    }

    public DrinkOption[] getDrinkOptions() {
        return drinkOptionList.get().toArray(new DrinkOption[0]);
    }

    public void addDrinkOption(DrinkOption drinkOption) {
        this.drinkOptionList.get().add(drinkOption);
    }

    public void load(String path) throws IOException {
        load(Path.of(path));
    }

    public void load(Path path) throws IOException {
        drinkOptionList.load(path, new TypeToken<List<DrinkOption>>() {});
    }

    public void save(String path) throws IOException {
        save(Path.of(path));
    }

    public void save(Path path) throws IOException {
        drinkOptionList.save(path);
    }

    /**
     * Registers a new drink option in the database.
     *
     * @param drinkOption the drink option to register
     */
    public void createNewDrinkOption(DrinkOption drinkOption) {
        for (DrinkOption d : drinkOptionList.get()) {
            if (d.getDrinkName().equals(drinkOption) && d.getColorName().equals(drinkOption)) {
                // If the drink option already exists, we don't need to add it again
                return;
            }
        }

        addDrinkOption(drinkOption);
    }

    /**
     * Checks if a drink option already exists in the database.
     *
     * @param drinkOption the drink option to check
     * @return true if the drink option exists, false otherwise
     */
    public static boolean drinkOptionExists(DrinkOption drinkOption) {
        for (DrinkOption d : drinkOptionList.get()) {
            if (d.getDrinkName().equals(drinkOption) && d.getColorName().equals(drinkOption)) {
                return true;
            }
        }

        return false;
    }

}