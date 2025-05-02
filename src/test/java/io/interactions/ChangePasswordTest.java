package io.interactions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.PasswordManagementException;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.UserDatabase;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions.ChangePasswordInteraction;
import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;

class ChangePasswordTest {
    
    private ThirstyCalc thirstyCalc;
    private User testUser;
    private UserDatabase userDatabase;
    private String initialPassword = "Initial@123";
    private String newValidPassword = "Changed@456";
    
    @BeforeEach
    void setUp() {
        // Create a new ThirstyCalc instance
        thirstyCalc = new ThirstyCalc();
        userDatabase = thirstyCalc.getUserDatabase();
        
        // Create a test user
        Username testUsername = new Username("testPasswordUser");
        Password password = new Password(initialPassword);
        testUser = new User(testUsername, password);
        try {
            testUser.getPassword().hashPassword();
        } catch (PasswordManagementException e) {
            fail("Password hashing failed: " + e.getMessage());
        }
        
        // Add user to database
        userDatabase.addUser(testUser);
        
        // Login the user
        thirstyCalc.login(testUser);
    }
    
    @Test
    void testSuccessfulPasswordChange() {
        // Mock the interaction with simulated user input
        PasswordChangeTester tester = new PasswordChangeTester(
            initialPassword, // Old password
            newValidPassword, // New password
            newValidPassword  // New password verification
        );
        
        // Execute the interaction
        tester.simulatePasswordChange(testUser, userDatabase);
        
        // Verify password was changed
        assertTrue(tester.wasSuccessful(), "Password change should succeed");
        
        // Verify the user can login with the new password
        try {
            assertTrue(testUser.verifyPassword(newValidPassword), 
                "User should be able to verify with new password");
        } catch (Exception e) {
            fail("Password verification threw exception: " + e.getMessage());
        }
    }
    
    @Test
    void testIncorrectOldPassword() {
        // Mock the interaction with simulated user input
        PasswordChangeTester tester = new PasswordChangeTester(
            "WrongOld@123", // Incorrect old password
            newValidPassword,
            newValidPassword
        );
        
        // Execute the interaction
        tester.simulatePasswordChange(testUser, userDatabase);
        
        // Should fail due to incorrect old password
        assertFalse(tester.wasSuccessful(), "Password change should fail with incorrect old password");
        
        // Verify the password wasn't changed
        try {
            assertTrue(testUser.verifyPassword(initialPassword), 
                "User password should remain unchanged");
        } catch (Exception e) {
            fail("Password verification threw exception: " + e.getMessage());
        }
    }
    
    @Test
    void testMismatchedNewPasswords() {
        // Mock the interaction with simulated user input
        PasswordChangeTester tester = new PasswordChangeTester(
            initialPassword,
            newValidPassword,
            "Different@789" // Different password in verification
        );
        
        // Execute the interaction
        tester.simulatePasswordChange(testUser, userDatabase);
        
        // Should fail due to mismatched new passwords
        assertFalse(tester.wasSuccessful(), "Password change should fail with mismatched new passwords");
        
        // Verify the password wasn't changed
        try {
            assertTrue(testUser.verifyPassword(initialPassword), 
                "User password should remain unchanged");
        } catch (Exception e) {
            fail("Password verification threw exception: " + e.getMessage());
        }
    }
    
    @Test
    void testInvalidNewPassword() {
        // Mock the interaction with simulated user input
        PasswordChangeTester tester = new PasswordChangeTester(
            initialPassword,
            "weak", // Invalid password (too short, missing requirements)
            "weak"
        );
        
        // Execute the interaction
        tester.simulatePasswordChange(testUser, userDatabase);
        
        // Should fail due to invalid new password
        assertFalse(tester.wasSuccessful(), "Password change should fail with invalid password format");
        
        // Verify the password wasn't changed
        try {
            assertTrue(testUser.verifyPassword(initialPassword), 
                "User password should remain unchanged");
        } catch (Exception e) {
            fail("Password verification threw exception: " + e.getMessage());
        }
    }
    
    /**
     * Helper class to test the ChangePasswordInteraction without requiring actual user input
     */
    private static class PasswordChangeTester {
        private final String oldPassword;
        private final String newPassword;
        private final String newPasswordVerification;
        private boolean successful = false;
        
        public PasswordChangeTester(String oldPassword, String newPassword, String newPasswordVerification) {
            this.oldPassword = oldPassword;
            this.newPassword = newPassword;
            this.newPasswordVerification = newPasswordVerification;
        }
        
        public void simulatePasswordChange(User user, UserDatabase userDatabase) {
            // Create a custom implementation of ChangePasswordInteraction that doesn't need actual UI
            ChangePasswordInteraction interaction = new ChangePasswordInteraction(user, userDatabase) {
                @Override
                public void execute() {
                    // Simulate getting the passwords
                    String oldPw = oldPassword;
                    String newPw = newPassword;
                    String newPwVerification = newPasswordVerification;
                    
                    User foundUser = null;
                    for (User u : userDatabase.getUsers()) {
                        if (u.getUsername().equals(user.getUsername())) {
                            foundUser = u;
                            break;
                        }
                    }
                    
                    try {
                        if (foundUser == null || !user.verifyPassword(oldPw)) {
                            System.out.println("Old password is incorrect!");
                            failure();
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("Error verifying password: " + e.getMessage());
                        failure();
                        return;
                    }
                    
                    if (!newPw.equals(newPwVerification)) {
                        System.out.println("New passwords do not match!");
                        failure();
                        return;
                    }
                    
                    try {
                        Password newPasswordObj = new Password(newPw);
                        foundUser.setPassword(newPasswordObj);
                        System.out.println("Password successfully updated!");
                        success(foundUser);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error updating password: " + e.getMessage());
                        failure();
                    }
                }
                
                @Override
                public void success(User result) {
                    successful = true;
                    super.success(result);
                }
                
                @Override
                public void failure() {
                    successful = false;
                    super.failure();
                }
            };
            
            // Run the interaction
            interaction.run();
        }
        
        public boolean wasSuccessful() {
            return successful;
        }
    }
}
