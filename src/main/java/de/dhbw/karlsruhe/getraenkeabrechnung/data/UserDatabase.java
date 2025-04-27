package de.dhbw.karlsruhe.getraenkeabrechnung.data;

import com.google.gson.reflect.TypeToken;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private Savable<List<User>> users;

    public UserDatabase() {
        this.users = new Savable<>(new ArrayList<>());
    }

    public User[] getUsers() {
        return users.get().toArray(new User[0]);
    }

    public void addUser(User user) {
        this.users.get().add(user);
    }

    public void registerNewUser(User user) {
        for (User u : users.get()) {
            if (u.getUsername().equals(user.getUsername())) {
                return;
            } else {
                addUser(user);
            }
        }

        users.get().add(user);
    }

    public boolean userExists(Username username) {
        for (User u : users.get()) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }


    public void load(String path) throws IOException {
        load(Path.of(path));
    }

    public void load(Path path) throws IOException {
        users.load(path, new TypeToken<List<User>>() {});
    }

    public void save(String path) throws IOException {
        save(Path.of(path));
    }

    public void save(Path path) throws IOException {
        users.save(path);
    }
}
