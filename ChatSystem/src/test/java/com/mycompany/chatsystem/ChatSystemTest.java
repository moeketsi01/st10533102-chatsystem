package com.mycompany.chatsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ChatSystem Test Suite")
public class ChatSystemTest {

    // =========================
    // USERNAME VALIDATION TESTS
    // =========================
    @Test
    @DisplayName("Valid username with underscore and 5 characters")
    void testCheckUserNameValid() {
        assertTrue(ChatSystem.checkUserName("user_"), 
            "Username 'user_' should be valid");
    }

    @Test
    @DisplayName("Valid username with underscore and 4 characters")
    void testCheckUserNameValidShort() {
        assertTrue(ChatSystem.checkUserName("us_r"), 
            "Username 'us_r' should be valid");
    }

    @Test
    @DisplayName("Invalid username without underscore")
    void testCheckUserNameNoUnderscore() {
        assertFalse(ChatSystem.checkUserName("user123"), 
            "Username without underscore should be invalid");
    }

    @Test
    @DisplayName("Invalid username exceeding 5 characters")
    void testCheckUserNameTooLong() {
        assertFalse(ChatSystem.checkUserName("user_123"), 
            "Username exceeding 5 characters should be invalid");
    }

    @Test
    @DisplayName("Invalid username with underscore but too long")
    void testCheckUserNameWithUnderscoreTooLong() {
        assertFalse(ChatSystem.checkUserName("user_12"), 
            "Username 'user_12' exceeds 5 characters and should be invalid");
    }

    // =========================
    // PASSWORD COMPLEXITY TESTS
    // =========================
    @Test
    @DisplayName("Valid password with all requirements")
    void testCheckPasswordComplexityValid() {
        assertTrue(ChatSystem.checkPasswordComplexity("MyPass@123"), 
            "Password 'MyPass@123' should be valid");
    }

    @Test
    @DisplayName("Valid password with special character !")
    void testCheckPasswordComplexityValidWithExclamation() {
        assertTrue(ChatSystem.checkPasswordComplexity("Secure!Pass99"), 
            "Password with ! should be valid");
    }

    @Test
    @DisplayName("Invalid password without uppercase letter")
    void testCheckPasswordComplexityNoUppercase() {
        assertFalse(ChatSystem.checkPasswordComplexity("mypass@123"), 
            "Password without uppercase should be invalid");
    }

    @Test
    @DisplayName("Invalid password without number")
    void testCheckPasswordComplexityNoNumber() {
        assertFalse(ChatSystem.checkPasswordComplexity("MyPass@abc"), 
            "Password without number should be invalid");
    }

    @Test
    @DisplayName("Invalid password without special character")
    void testCheckPasswordComplexityNoSpecialChar() {
        assertFalse(ChatSystem.checkPasswordComplexity("MyPass123"), 
            "Password without special character should be invalid");
    }

    @Test
    @DisplayName("Invalid password too short")
    void testCheckPasswordComplexityTooShort() {
        assertFalse(ChatSystem.checkPasswordComplexity("My@1"), 
            "Password with less than 8 characters should be invalid");
    }

    // =========================
    // CELL PHONE NUMBER VALIDATION TESTS
    // =========================
    @Test
    @DisplayName("Valid SA cell phone number")
    void testCheckCellPhoneNumberValid() {
        assertTrue(ChatSystem.checkCellPhoneNumber("+27123456789"), 
            "Valid SA phone number should pass");
    }

    @Test
    @DisplayName("Valid SA cell phone number with different digits")
    void testCheckCellPhoneNumberValidAlternative() {
        assertTrue(ChatSystem.checkCellPhoneNumber("+27987654321"), 
            "Valid SA phone number should pass");
    }

    @Test
    @DisplayName("Invalid phone number without country code")
    void testCheckCellPhoneNumberNoCountryCode() {
        assertFalse(ChatSystem.checkCellPhoneNumber("0123456789"), 
            "Phone number without country code should be invalid");
    }

    @Test
    @DisplayName("Invalid phone number with wrong country code")
    void testCheckCellPhoneNumberWrongCountryCode() {
        assertFalse(ChatSystem.checkCellPhoneNumber("+26123456789"), 
            "Phone number with wrong country code should be invalid");
    }

    @Test
    @DisplayName("Invalid phone number too short")
    void testCheckCellPhoneNumberTooShort() {
        assertFalse(ChatSystem.checkCellPhoneNumber("+2712345678"), 
            "Phone number too short should be invalid");
    }

    @Test
    @DisplayName("Invalid phone number too long")
    void testCheckCellPhoneNumberTooLong() {
        assertFalse(ChatSystem.checkCellPhoneNumber("+271234567890"), 
            "Phone number too long should be invalid");
    }

    // =========================
    // REGISTER USER TESTS
    // =========================
    @Test
    @DisplayName("Successfully register user with valid credentials")
    void testRegisterUserSuccess() {
        String result = ChatSystem.registerUser("test_", "MyPass@123", "+27123456789");
        assertEquals("User has been registered successfully.", result, 
            "Should successfully register user with valid credentials");
    }

    @Test
    @DisplayName("Register fails with invalid username")
    void testRegisterUserInvalidUsername() {
        String result = ChatSystem.registerUser("invalidusername", "MyPass@123", "+27123456789");
        assertTrue(result.contains("Username is not correctly formatted"), 
            "Should fail with invalid username message");
    }

    @Test
    @DisplayName("Register fails with invalid password")
    void testRegisterUserInvalidPassword() {
        String result = ChatSystem.registerUser("test_", "weak", "+27123456789");
        assertTrue(result.contains("Password is not correctly formatted"), 
            "Should fail with invalid password message");
    }

    @Test
    @DisplayName("Register fails with invalid phone number")
    void testRegisterUserInvalidPhone() {
        String result = ChatSystem.registerUser("test_", "MyPass@123", "0123456789");
        assertTrue(result.contains("Cell phone number incorrectly formatted"), 
            "Should fail with invalid phone message");
    }

    // =========================
    // LOGIN USER TESTS
    // =========================
    @Test
    @DisplayName("Successful login with correct credentials")
    void testLoginUserSuccess() {
        ChatSystem.registerUser("user_", "Pass@123", "+27123456789");
        assertTrue(ChatSystem.loginUser("user_", "Pass@123"), 
            "Should successfully login with correct credentials");
    }

    @Test
    @DisplayName("Login fails with incorrect username")
    void testLoginUserWrongUsername() {
        ChatSystem.registerUser("user_", "Pass@123", "+27123456789");
        assertFalse(ChatSystem.loginUser("wrong_", "Pass@123"), 
            "Should fail login with incorrect username");
    }

    @Test
    @DisplayName("Login fails with incorrect password")
    void testLoginUserWrongPassword() {
        ChatSystem.registerUser("user_", "Pass@123", "+27123456789");
        assertFalse(ChatSystem.loginUser("user_", "WrongPass@123"), 
            "Should fail login with incorrect password");
    }

    // =========================
    // LOGIN STATUS TESTS
    // =========================
    @Test
    @DisplayName("Return successful login status message")
    void testReturnLoginStatusSuccess() {
        ChatSystem.firstName = "John";
        ChatSystem.lastName = "Doe";
        String result = ChatSystem.returnLoginStatus(true);
        assertTrue(result.contains("Welcome John, Doe"), 
            "Should return welcome message for successful login");
    }

    @Test
    @DisplayName("Return failed login status message")
    void testReturnLoginStatusFailure() {
        String result = ChatSystem.returnLoginStatus(false);
        assertTrue(result.contains("Username or password incorrect"), 
            "Should return error message for failed login");
    }

    // =========================
    // MESSAGE ID VALIDATION TESTS
    // =========================
    @Test
    @DisplayName("Valid message ID of 10 characters")
    void testCheckMessageIDValid() {
        ChatSystem chatSystem = new ChatSystem();
        assertTrue(chatSystem.checkMessageID("1234567890"), 
            "Message ID with 10 characters should be valid");
    }

    @Test
    @DisplayName("Valid message ID under 10 characters")
    void testCheckMessageIDValidShort() {
        ChatSystem chatSystem = new ChatSystem();
        assertTrue(chatSystem.checkMessageID("12345"), 
            "Message ID with less than 10 characters should be valid");
    }

    @Test
    @DisplayName("Invalid message ID exceeding 10 characters")
    void testCheckMessageIDInvalid() {
        ChatSystem chatSystem = new ChatSystem();
        assertFalse(chatSystem.checkMessageID("12345678901"), 
            "Message ID exceeding 10 characters should be invalid");
    }

    // =========================
    // RECIPIENT CELL VALIDATION TESTS
    // =========================
    @Test
    @DisplayName("Valid recipient cell phone number")
    void testCheckRecipientCellValid() {
        ChatSystem chatSystem = new ChatSystem();
        assertTrue(chatSystem.checkRecipientCell("+27123456789"), 
            "Valid recipient cell should pass");
    }

    @Test
    @DisplayName("Invalid recipient cell without country code")
    void testCheckRecipientCellNoCountryCode() {
        ChatSystem chatSystem = new ChatSystem();
        assertFalse(chatSystem.checkRecipientCell("0123456789"), 
            "Recipient cell without country code should fail");
    }

    // =========================
    // MESSAGE HASH CREATION TESTS
    // =========================
    @Test
    @DisplayName("Create message hash with single word message")
    void testCreateMessageHashSingleWord() {
        ChatSystem chatSystem = new ChatSystem();
        chatSystem.messageID = "1234567890";
        chatSystem.messageText = "Hello";
        ChatSystem.totalMessages = 5;
        
        String hash = chatSystem.createMessageHash();
        assertTrue(hash.contains("12:5:HELLOHELLO"), 
            "Hash should contain message ID start, total messages, and first/last word");
    }

    @Test
    @DisplayName("Create message hash with multiple word message")
    void testCreateMessageHashMultipleWords() {
        ChatSystem chatSystem = new ChatSystem();
        chatSystem.messageID = "1234567890";
        chatSystem.messageText = "Hello World Test";
        ChatSystem.totalMessages = 3;
        
        String hash = chatSystem.createMessageHash();
        assertTrue(hash.contains("12:3:HELLOTEST"), 
            "Hash should use first and last words");
    }

    // =========================
    // TOTAL MESSAGES TESTS
    // =========================
    @Test
    @DisplayName("Return total messages count")
    void testReturnTotalMessages() {
        ChatSystem chatSystem = new ChatSystem();
        ChatSystem.totalMessages = 10;
        assertEquals(10, chatSystem.returnTotalMessages(), 
            "Should return correct total messages count");
    }

    @Test
    @DisplayName("Total messages counter increments")
    void testTotalMessagesIncrement() {
        ChatSystem.totalMessages = 0;
        assertEquals(0, ChatSystem.totalMessages);
        ChatSystem.totalMessages++;
        assertEquals(1, ChatSystem.totalMessages);
    }
}
