package de.dhbw.karlsruhe.getraenkeabrechnung.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Savable<T> {
    private T t;

    public Savable(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public void load(String path, TypeToken<T> typeToken) throws IOException {
        load(Path.of(path), typeToken);
    }

    /**
     * Loads the data from the file system.
     * We need to provide a TypeToken so the parsing can be done correctly. See
     * <a href="https://github.com/google/gson/blob/main/UserGuide.md#collections-examples">here</a>.
     *
     * @param path      the path to the file
     * @param typeToken the type of the parsed data
     * @throws IOException is thrown when the file cannot be read
     */
    public void load(Path path, TypeToken<T> typeToken) throws IOException {
        Gson gson = new Gson();

        t = gson.fromJson(Files.readString(path), typeToken.getType());
    }

    public void save(String path) throws IOException {
        save(Path.of(path));
    }

    public void save(Path path) throws IOException {
        Gson gson = new Gson();
        Files.writeString(path, gson.toJson(t));
    }
}
