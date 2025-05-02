package data;

import com.google.gson.Gson;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.banking.Account;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.banking.AccountDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountDatabaseTest {
    @Test
    public void itSavesAccounts(@TempDir Path tempDir) {
        User user = new User(new Username("Dings"), new Password("bums"));
        Account[] accounts = {new Account(user.getUsername())};

        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.createAccount(user);

        Path filepath = tempDir.resolve("accounts.json");
        Gson gson = new Gson();

        assertDoesNotThrow(() -> {
            accountDatabase.save(filepath);
            assertEquals(Files.readString(filepath), gson.toJson(accounts));
        });
    }

    @Test
    public void itLoadsAccounts(@TempDir Path tempDir) {
        User user1 = new User(new Username("Dings"), new Password("dings"));
        User user2 = new User(new Username("Bums"), new Password("bums"));

        Account account1 = new Account(user1.getUsername());
        Account account2 = new Account(user2.getUsername());

        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.createAccount(user1);
        accountDatabase.createAccount(user2);

        Path filepath = tempDir.resolve("accounts.json");
        int initLength = accountDatabase.getAccounts().length;

        assertDoesNotThrow(() -> {
            accountDatabase.save(filepath);
            accountDatabase.load(filepath);

            assertEquals(initLength, accountDatabase.getAccounts().length);
            assertEquals(account1, accountDatabase.getAccountOfUser(user1));
            assertEquals(account2, accountDatabase.getAccountOfUser(user2));
        });
    }
}
