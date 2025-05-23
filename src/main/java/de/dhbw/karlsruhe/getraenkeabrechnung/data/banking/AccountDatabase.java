package de.dhbw.karlsruhe.getraenkeabrechnung.data.banking;

import com.google.gson.reflect.TypeToken;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.Savable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AccountDatabase
{
    private Savable<List<Account>> accounts;

    public AccountDatabase()
    {
        this.accounts = new Savable<>(new ArrayList<>());
    }

    public Account[] getAccounts()
    {
        return accounts.get().toArray(new Account[0]);
    }

    public void createAccount(User user)
    {
        this.accounts.get().add(new Account(user.getUsername()));
    }

    public Account getAccountOfUser(User user)
    {
        for (Account a : accounts.get())
        {
            if (a.getUsername().equals(user.getUsername()))
            {
                return a;
            }
        }

        throw new AccountDoesNotExistException("Account with the username " + user.getUsername() + " does not exist");
    }


    public boolean checkIfAccountBalanceIsZero(User user)
    {
        for (Account a : accounts.get())
        {
            if (a.getUsername().equals(user.getUsername()) && a.isEmpty())
            {
                return true;
            }
        }
        return false;
    }

    public void removeAccount(User user)
    {
        for (Account a : accounts.get())
        {
            if (a.getUsername().equals(user.getUsername()))
            {
                accounts.get().remove(a);
                return;
            }
        }
    }

    public void load(String path) throws IOException
    {
        load(Path.of(path));
    }

    public void load(Path path) throws IOException
    {
        accounts.load(path, new TypeToken<List<Account>>() {});
    }

    public void save(String path) throws IOException
    {
        save(Path.of(path));
    }

    public void save(Path path) throws IOException
    {
        accounts.save(path);
    }
}
