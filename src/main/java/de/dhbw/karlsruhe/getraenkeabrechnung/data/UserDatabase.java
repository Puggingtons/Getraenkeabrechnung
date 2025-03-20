package de.dhbw.karlsruhe.getraenkeabrechnung.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private List<User> users;
    private static final String PATH = "users.json";

    public UserDatabase() {
        this.users = new ArrayList<>();
    }

    public User[] getUsers() {
        return users.toArray(new User[0]);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void registerNewUser(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return;
            }
        }

        users.add(user);
    }

    public boolean userExists(Username username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    public void load() throws IOException {
        load(PATH);
    }

    public void load(String path) throws IOException {
        load(Path.of(path));
    }

    public void load(Path path) throws IOException {
        Gson gson = new Gson();

        // https://github.com/google/gson/blob/main/UserGuide.md#collections-examples
        TypeToken<List<User>> typeToken = new TypeToken<List<User>>() {};
        this.users = gson.fromJson(Files.readString(path), typeToken.getType());
    }

    public void save() throws IOException {
        save(PATH);
    }

    public void save(String path) throws IOException {
        save(Path.of(path));
    }

    public void save(Path path) throws IOException {
        Gson gson = new Gson();
        Files.writeString(path, gson.toJson(users));
    }

}
