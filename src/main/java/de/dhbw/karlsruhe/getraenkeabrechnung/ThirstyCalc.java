package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.banking.Account;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.AccountDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.DrinkDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.state.ApplicationState;

import java.io.IOException;

public class ThirstyCalc {
    private final UserDatabase userDatabase;
    private final AccountDatabase accountDatabase;
    private final DrinkDatabase drinkDatabase;

    private final ApplicationState applicationState;

    public ThirstyCalc() {
        userDatabase = new UserDatabase();
        accountDatabase = new AccountDatabase();

        drinkDatabase = new DrinkDatabase();

        applicationState = new ApplicationState();

        greet();
    }

    private void greet() {
        System.out.print("""
                
                  _____    _   _      ___      ____     ____     _____   __   __   ____     _       _        ____
                 |_ " _|  |'| |'|    | "_|  U |  _"\\ u / __"| u |_ " _|  \\ \\ / /U /"___|U  /"\\  u  |"|    U /"___|
                   | |   /| |_| |\\    | |    \\| |_) |/<\\___ \\/    | |     \\ V / \\| | u   \\/ _ \\/ U | | u  \\| | u
                  /| |\\  U|  _  |u    | |     |  _ <   u___) |   /| |\\   U_|"|_u | |/__  / ___ \\  \\| |/__  | |/__
                 u |_|U   |_| |_|   U/| |\\u   |_| \\_\\  |____/>> u |_|U     |_|    \\____|/_/   \\_\\  |_____|  \\____|
                 _// \\\\_  //   \\\\.-,_|___|_,-.//   \\\\_  )(  (__)_// \\\\_.-,//|(_  _// \\\\  \\\\    >>  //  \\\\  _// \\\\
                (__) (__)(_" )("_)\\_)-' '-(_/(__)  (__)(__)    (__) (__)\\_) (__)(__)(__)(__)  (__)(_" )("_)(__)(__)
                
                """);
    }

    public void login(User user) {
        if (!userDatabase.userExists(user.getUsername())) {
            // todo handle user does not exist
            System.out.println("user does not exist");
            return;
        }

        if (applicationState.isLoggedIn()) {
            // todo handle user is already logged in
            System.out.println("user already logged in");
            return;
        }

        applicationState.setLoggedInUser(user);
    }

    public void logout() {
        applicationState.clearLoggedInUser();
    }

    public void createNewUser(User user) {
        userDatabase.registerNewUser(user);
        accountDatabase.createAccount(user);
        System.out.println("Created new user: " + user.getUsername());
    }

    public void registerNewUser(User user) {
        userDatabase.registerNewUser(user);
        accountDatabase.createAccount(user);
        System.out.println("Registered new user: " + user.getUsername());
    }

    public void createNewDrinkOption(DrinkOption drinkOption) {
        drinkDatabase.createNewDrinkOption(drinkOption);
        System.out.println("Creating a new drink option: " + drinkOption);
    }

    public boolean drinkOptionExists(DrinkName drinkName) {
        return drinkDatabase.drinkOptionExists(drinkName);
    }
    

    public void deleteUser(User user) {
        userDatabase.deleteUser(user);
        accountDatabase.removeAccount(user);
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public UserDatabase getUserDatabase() {
        return userDatabase;
    }

    public AccountDatabase getAccountDatabase() {
        return accountDatabase;
    }

    public Account getAccountOfLoggedInUser() {
        return accountDatabase.getAccountOfUser(applicationState.getLoggedInUser());
    }

    public void save() {
        try {
            System.out.println("Saving users.json");
            userDatabase.save("users.json");

            System.out.println("Saving accounts.json");
            accountDatabase.save("accounts.json");

            System.out.println("Saving drinks.json");
            drinkDatabase.save("drinks.json");

        } catch (IOException e) {
            System.out.println("Could not save users");
        }
    }

    public void load() {
        try {
            userDatabase.load("users.json");
            accountDatabase.load("accounts.json");
            drinkDatabase.load("drinks.json");

        } catch (IOException e) {
            System.out.println("Could not load users, accounts or drinks!");
        }
    }
}
