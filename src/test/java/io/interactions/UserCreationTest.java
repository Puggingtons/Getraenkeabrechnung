package io.interactions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.PasswordManagementException;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.state.ApplicationState;
import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;

public class UserCreationTest {
    
    //todo: create tests using existing mocks and test the specific interactions
    
    @Test
    public void testUserCreationAndLoginFlow() {
        // Create a ThirstyCalc instance for the complete flow
        ThirstyCalc thirstyCalc = new ThirstyCalc();
        UserDatabase database = thirstyCalc.getUserDatabase();
        ApplicationState appState = thirstyCalc.getApplicationState();
        
        // Create a user directly
        String username = "flowTestUser";
        String password = "Flow@Test123";
        
        User testUser = new User(new Username(username), new Password(password));
        try {
            testUser.getPassword().hashPassword();
        } catch (PasswordManagementException e) {
            fail("Password hashing failed: " + e.getMessage());
        }
        database.addUser(testUser);
        
        // Test login via ThirstyCalc
        // First check that no user is logged in
        assertFalse(appState.isLoggedIn());
        
        // Login
        thirstyCalc.login(testUser);
        
        // Verify login state
        assertTrue(appState.isLoggedIn());
        assertEquals(username, appState.getLoggedInUser().getUsername().toString());
        
        // Test logout
        thirstyCalc.logout();
        assertFalse(appState.isLoggedIn());
        assertNull(appState.getLoggedInUser());
    }
}
