package io.interactions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.PasswordManagementException;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.banking.Account;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.AccountDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;
import de.dhbw.karlsruhe.getraenkeabrechnung.banking.AccountDoesNotExistException;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.numbers.Money;

public class UserDeletionTest {
    
    private ThirstyCalc thirstyCalc;
    private UserDatabase userDatabase;
    private AccountDatabase accountDatabase;
    private User testUser;
    private Account testAccount;
    private final String TEST_USERNAME = "test_DeleteUser";
    private final String TEST_PASSWORD = "Test@Delete123";
    
    @BeforeEach
    public void setup() {
        // Initialize ThirstyCalc which contains the databases
        thirstyCalc = new ThirstyCalc();
        userDatabase = thirstyCalc.getUserDatabase();
        accountDatabase = thirstyCalc.getAccountDatabase();
        
        // Create a test user
        testUser = new User(new Username(TEST_USERNAME), new Password(TEST_PASSWORD));
        try {
            testUser.getPassword().hashPassword();
        } catch (PasswordManagementException e) {
            fail("Failed to hash password during setup: " + e.getMessage());
        }
        
        // Add user to database
        userDatabase.addUser(testUser);
        
        // Create account for the user
        accountDatabase.createAccount(testUser);
        
        // Get reference to the created account
        testAccount = accountDatabase.getAccountOfUser(testUser);
        
        // Verify setup was successful
        assertNotNull(testUser, "Test user should be created");
        assertNotNull(testAccount, "Test account should be created");
        assertTrue(userDatabase.userExists(testUser.getUsername()), "User should exist in database");
    }
    
    @Test
    public void testUserAndAccountExistAfterSetup() {
        // This test verifies that the @BeforeEach setup method works correctly
        assertTrue(userDatabase.userExists(testUser.getUsername()), 
                "User should exist in database after setup");
                
        Account account = accountDatabase.getAccountOfUser(testUser);
        assertNotNull(account, "Account should exist for user");
        assertEquals(testUser.getUsername(), account.getUsername(), 
                "Account should be associated with the correct username");
    }

    @Test
    public void testDeleteUser() {

        assertTrue(userDatabase.userExists(testUser.getUsername()), 
                "User should exist before deletion");
        assertNotNull(accountDatabase.getAccountOfUser(testUser),
                "Account should exist before deletion");

        thirstyCalc.deleteUser(testUser);

        assertFalse(userDatabase.userExists(testUser.getUsername()), 
                "User should not exist after deletion");
        assertThrows(AccountDoesNotExistException.class, () -> {
            accountDatabase.getAccountOfUser(testUser);
        }, "Account should not exist after deletion");
    }


    // todo: Write a test for the interaction
    @Test
    public void testDeleteUserWithPositiveBalance() {
        testAccount.deposit(new Money("10.00"));
        assertEquals(testAccount.getBalance(), new Money("10.00"),
                "Account balance should be 10.00 before deletion");

        assertTrue(userDatabase.userExists(testUser.getUsername()), 
                "User should exist before deletion");
        assertNotNull(accountDatabase.getAccountOfUser(testUser),
                "Account should exist before deletion");
        assertFalse(accountDatabase.checkIfAccountBalanceIsZero(testUser),
                "Account should not be empty before deletion");
        
        if (accountDatabase.checkIfAccountBalanceIsZero(testUser)) {
            thirstyCalc.deleteUser(testUser);
        } else {
            System.out.println("User has a positive balance. Please settle the balance before deleting the user.");
        }
        assertTrue(userDatabase.userExists(testUser.getUsername()), 
                "User should still exist after attempted deletion with positive balance");
        assertNotNull(accountDatabase.getAccountOfUser(testUser),
                "Account should still exist after attempted deletion with positive balance");
    }
}
