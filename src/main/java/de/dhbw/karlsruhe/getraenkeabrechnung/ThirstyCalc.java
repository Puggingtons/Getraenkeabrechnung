package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.banking.Account;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.banking.AccountDatabase;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.DrinkDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.DrinkName;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.DrinkOption;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.CategoryName;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.CategoryOption;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.CategoryDatabase;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.UserDatabase;

import de.dhbw.karlsruhe.getraenkeabrechnung.logging.Logger;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.LoggerFactory;
import de.dhbw.karlsruhe.getraenkeabrechnung.logging.UserLogger;

import de.dhbw.karlsruhe.getraenkeabrechnung.rights.AdminRights;
import de.dhbw.karlsruhe.getraenkeabrechnung.state.ApplicationState;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;

import java.io.IOException;

public class ThirstyCalc
{
    private final UserDatabase userDatabase;
    private final AccountDatabase accountDatabase;
    private final DrinkDatabase drinkDatabase;
    private final CategoryDatabase categoryDatabase;

    private final ApplicationState applicationState;

    private Logger logger;

    public ThirstyCalc()
    {
        this(new LoggerFactory().defaultTimeLogger());
    }

    public ThirstyCalc(Logger logger)
    {
        this.logger = logger;

        userDatabase = new UserDatabase();
        accountDatabase = new AccountDatabase();

        categoryDatabase = new CategoryDatabase();
        drinkDatabase = new DrinkDatabase();

        applicationState = new ApplicationState();

        greet();
    }

    private void greet()
    {
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

    public void login(User user)
    {
        if (!userDatabase.userExists(user.getUsername()))
        {
            // todo handle user does not exist
            System.out.println("user does not exist");
            logger.log("failed login attempt for user " + user.getUsername() + " because user does not exist");
            return;
        }

        if (applicationState.isLoggedIn())
        {
            // todo handle user is already logged in
            System.out.println("user already logged in");
            logger.log("failed login attempt for user " + user.getUsername() + " because there is already a logged in user");
            return;
        }

        applicationState.setLoggedInUser(user);

        this.logger = new UserLogger(user, this.logger);
    }

    public void logout()
    {
        logger.log("user " + applicationState.getLoggedInUser().getUsername() + " logged out");
        applicationState.clearLoggedInUser();
        this.logger = this.logger.getInnerLogger();
    }

    public void createNewUser(User user)
    {
        logger.log("creating new user " + user.getUsername());
        userDatabase.registerNewUser(user);
        accountDatabase.createAccount(user);
        System.out.println("Created new user: " + user.getUsername());
    }

    public void registerNewUser(User user)
    {
        userDatabase.registerNewUser(user);
        accountDatabase.createAccount(user);
        System.out.println("Registered new user: " + user.getUsername());
    }

    public void changePassword(User user)
    {
        userDatabase.updateUser(user);
        System.out.println("Changed password for user: " + user.getUsername());
    }

    public void createNewCategoryOption(CategoryOption categoryOption)
    {
        logger.log("creating new category option " + categoryOption.getColorName() + " with price " + categoryOption.getColorPrice());
        categoryDatabase.createNewCategoryOption(categoryOption);
        System.out.println("Creating a new category option: " + categoryOption.getColorName());
    }

    public boolean categoryOptionExists(CategoryOption categoryOption)
    {
        return categoryDatabase.categoryOptionExists(categoryOption);
    }

    public void createNewDrinkOption(DrinkOption drinkOption)
    {
        logger.log("creating new drink option " + drinkOption.getDrinkName() + " with color " + drinkOption.getColorName());
        drinkDatabase.createNewDrinkOption(drinkOption);
        System.out.println("Creating a new drink option: " + drinkOption.getDrinkName());
    }

    public boolean drinkOptionExists(DrinkOption drinkOption)
    {
        return drinkDatabase.drinkOptionExists(drinkOption);
    }


    public void deleteUser(User user)
    {
        logger.log("deleting user " + user.getUsername());
        userDatabase.deleteUser(user);
        accountDatabase.removeAccount(user);
    }

    public ApplicationState getApplicationState()
    {
        return applicationState;
    }

    public UserDatabase getUserDatabase()
    {
        return userDatabase;
    }

    public AccountDatabase getAccountDatabase()
    {
        return accountDatabase;
    }

    public CategoryDatabase getCategoryDatabase()
    {
        return categoryDatabase;
    }

    public DrinkDatabase getDrinkDatabase()
    {
        return drinkDatabase;
    }

    public Account getAccountOfLoggedInUser()
    {
        return accountDatabase.getAccountOfUser(applicationState.getLoggedInUser());
    }

    public void save()
    {
        try
        {
            logger.log("saving databases");

            logger.log("saving users.json");
            System.out.println("Saving users.json");
            userDatabase.save("users.json");

            logger.log("saving accounts.json");
            System.out.println("Saving accounts.json");
            accountDatabase.save("accounts.json");

            logger.log("saving drinks.json");
            System.out.println("Saving drinks.json");
            drinkDatabase.save("drinks.json");

            logger.log("saving categories.json");
            System.out.println("Saving categories.json");
            categoryDatabase.save("categories.json");

            logger.log("finished saving databases");
        } catch (IOException e)
        {
            System.out.println("Could not save users");
        }
    }

    public void load()
    {
        try
        {
            logger.log("loading databases");

            logger.log("loading users.json");
            userDatabase.load("users.json");

            logger.log("loading accounts.json");
            accountDatabase.load("accounts.json");

            logger.log("loading drinks.json");
            drinkDatabase.load("drinks.json");

            logger.log("loading categories.json");
            categoryDatabase.load("categories.json");

            logger.log("finished loading databases");

        } catch (IOException e)
        {
            System.out.println("Could not load users, accounts, categories or drinks!");
            createGenericAdminUser();
        }

        // If the database loaded but is empty, create an admin user
        if (userDatabase.getUsers().length == 0)
        {
            createGenericAdminUser();
        }
    }

    /**
     * Creates a generic admin user when no user database is detected or when database is empty.
     * This ensures there's always at least one user with admin rights to manage the system.
     */
    private void createGenericAdminUser()
    {
        try
        {
            // Create username and password objects
            Username adminUsername = new Username("admin");
            Password adminPassword = new Password("Admin123@");

            // Create new admin user
            User adminUser = new User();
            adminUser.setUsername(adminUsername);
            adminUser.setPassword(adminPassword);

            // Add all admin rights
            new AdminRights().giveTo(adminUser);

            // Add user to database and create account
            userDatabase.registerNewUser(adminUser);
            accountDatabase.createAccount(adminUser);

            System.out.println("Created generic admin user. Username: admin, Password: Admin123@");

            // Save the user database to persist the admin user
            save();
        } catch (Exception e)
        {
            System.out.println("Error creating generic admin user: " + e.getMessage());
        }
    }
}
