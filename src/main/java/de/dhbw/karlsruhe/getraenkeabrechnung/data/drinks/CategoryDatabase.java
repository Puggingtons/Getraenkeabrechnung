package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.Savable;

public class CategoryDatabase {
    private static Savable<List<CategoryOption>> categoryOptionList;

    public CategoryDatabase() {
        this.categoryOptionList = new Savable<>( new ArrayList<>());
    }
    
    public CategoryOption[] getCategoryOptions() {
        return categoryOptionList.get().toArray(new CategoryOption[0]);
    }

    public void addCategoryOption(CategoryOption categoryOption) {
        this.categoryOptionList.get().add(categoryOption);
    }

    public void load(String path) throws IOException {
        load(Path.of(path));
    }

    public void load(Path path) throws IOException {
        categoryOptionList.load(path, new TypeToken<List<CategoryOption>>() {});
    }

    public void save(String path) throws IOException {
        save(Path.of(path));
    }

    public void save(Path path) throws IOException {
        categoryOptionList.save(path);
    }

    /**
     * Registers a new category option in the database.
     * 
     * Hint: The Name of a category equals the color name of the category option.
     *       This behavior is used to match the current system of the domain model.
     *       There are colored cable ties that are used to identify the drinks category.
     *
     * @param categoryOption the category option to register
     */
    public void createNewCategoryOption(CategoryOption categoryOption) {
        for (CategoryOption c : categoryOptionList.get()) {
            if (c.getColorName().equals(categoryOption.getColorName())) {
                // If the category option already exists, we don't need to add it again
                return;
            }
        }

        addCategoryOption(categoryOption);
    }

    /**
     * Checks if a category option already exists in the database.
     *
     * @param categoryOption the category option to check
     * @return true if the category option already exists, false otherwise
     */
    public boolean categoryOptionExists(CategoryOption categoryOption) {
        for (CategoryOption c : categoryOptionList.get()) {
            if (c.getColorName().equals(categoryOption.getColorName()) && c.getColorPrice() == categoryOption.getColorPrice()) {
                return true;
            }
        }
        return false;
    }
    
}
