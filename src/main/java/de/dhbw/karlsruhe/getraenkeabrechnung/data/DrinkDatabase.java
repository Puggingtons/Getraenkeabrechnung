package de.dhbw.karlsruhe.getraenkeabrechnung.data;

import de.dhbw.karlsruhe.getraenkeabrechnung.DrinkOption; // Adjust the package path if necessary

import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class DrinkDatabase {

    private Savable<List<DrinkOption>> drinkOptionList;

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
            if (d.equals(drinkOption)) {
                return;
            }
        }

        drinkOptionList.get().add(drinkOption);
    }

    /**
     * Checks if a drink option already exists in the database.
     *
     * @param drinkOption the drink option to check
     * @return true if the drink option exists, false otherwise
     */
    public boolean drinkOptionExists(DrinkOption drinkOption) {
        for (DrinkOption d : drinkOptionList.get()) {
            if (d.equals(drinkOption)) {
                return true;
            }
        }

        return false;
    }

}