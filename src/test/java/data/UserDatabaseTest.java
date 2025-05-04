package data;

import com.google.gson.Gson;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.UserDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDatabaseTest
{
    @Test
    public void itSavesUsers(@TempDir Path tempDir)
    {
        User[] users = {new User(new Username("Dings"), new Password("bums"))};

        UserDatabase userDatabase = new UserDatabase();
        for (User user : users)
        {
            userDatabase.addUser(user);
        }

        Path filepath = tempDir.resolve("users.json");
        Gson gson = new Gson();

        assertDoesNotThrow(() ->
        {
            userDatabase.save(filepath);
            assertEquals(Files.readString(filepath), gson.toJson(users));
        });
    }

    @Test
    public void itLoadsUsers(@TempDir Path tempDir)
    {
        User user = new User(new Username("Dings"), new Password("bums"));
        User user2 = new User(new Username("Dings2"), new Password("bums"));

        UserDatabase userDatabase = new UserDatabase();
        userDatabase.addUser(user);
        userDatabase.addUser(user2);

        Path filepath = tempDir.resolve("users.json");

        int initLength = userDatabase.getUsers().length;

        assertDoesNotThrow(() ->
        {
            userDatabase.save(filepath);
            userDatabase.load(filepath);
            assertEquals(initLength, userDatabase.getUsers().length);
        });
    }
}
