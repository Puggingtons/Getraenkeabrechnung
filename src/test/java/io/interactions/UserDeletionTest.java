package io.interactions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.PasswordManagementException;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.banking.Account;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.AccountDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;

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
}
