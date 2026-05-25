package com.mycompany.chatsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SendingMessages Test Suite")
public class SendingMessagesTest {

    private SendingMessages sendingMessages;

    @BeforeEach
    void setUp() {
        sendingMessages = new SendingMessages();
        SendingMessages.totalMessages = 0;
    }

    // =========================
    // MESSAGE ID VALIDATION TESTS
    // =========================
    @Test
    @DisplayName("Valid message ID of 10 characters")
    void testCheckMessageIDValid10Chars() {
        assertTrue(sendingMessages.checkMessageID("1234567890"), 
            "Message ID with exactly 10 characters should be valid");
    }

    @Test
    @DisplayName("Valid message ID under 10 characters")
    void testCheckMessageIDValidShort() {
        assertTrue(sendingMessages.checkMessageID("12345"), 
            "Message ID with less than 10 characters should be valid");
    }

    @Test
    @DisplayName("Valid message ID with single character")
    void testCheckMessageIDValidSingleChar() {
        assertTrue(sendingMessages.checkMessageID("1"), 
            "Single character message ID should be valid");
    }

    @Test
    @DisplayName("Invalid message ID exceeding 10 characters")
    void testCheckMessageIDInvalidTooLong() {
        assertFalse(sendingMessages.checkMessageID("12345678901"), 
            "Message ID exceeding 10 characters should be invalid");
    }

    @Test
    @DisplayName("Invalid message ID significantly exceeding 10 characters")
    void testCheckMessageIDInvalidMuchTooLong() {
        assertFalse(sendingMessages.checkMessageID("123456789012345"), 
            "Message ID much longer than 10 characters should be invalid");
    }

    // =========================
    // RECIPIENT CELL VALIDATION TESTS
    // =========================
    @Test
    @DisplayName("Valid SA recipient cell number")
    void testCheckRecipientCellValid() {
        assertTrue(sendingMessages.checkRecipientCell("+27123456789"), 
            "Valid SA recipient cell should pass");
    }

    @Test
    @DisplayName("Valid SA recipient cell with different digits")
    void testCheckRecipientCellValidAlternative() {
        assertTrue(sendingMessages.checkRecipientCell("+27987654321"), 
            "Valid SA recipient cell should pass");
    }

    @Test
    @DisplayName("Invalid recipient cell without country code")
    void testCheckRecipientCellNoCountryCode() {
        assertFalse(sendingMessages.checkRecipientCell("0123456789"), 
            "Recipient cell without country code should be invalid");
    }

    @Test
    @DisplayName("Invalid recipient cell with wrong country code")
    void testCheckRecipientCellWrongCountryCode() {
        assertFalse(sendingMessages.checkRecipientCell("+26123456789"), 
            "Recipient cell with wrong country code should be invalid");
    }

    @Test
    @DisplayName("Invalid recipient cell too short")
    void testCheckRecipientCellTooShort() {
        assertFalse(sendingMessages.checkRecipientCell("+2712345678"), 
            "Recipient cell too short should be invalid");
    }

    @Test
    @DisplayName("Invalid recipient cell too long")
    void testCheckRecipientCellTooLong() {
        assertFalse(sendingMessages.checkRecipientCell("+271234567890"), 
            "Recipient cell too long should be invalid");
    }

    @Test
    @DisplayName("Invalid recipient cell with letters")
    void testCheckRecipientCellWithLetters() {
        assertFalse(sendingMessages.checkRecipientCell("+27abc123def"), 
            "Recipient cell with letters should be invalid");
    }

    @Test
    @DisplayName("Invalid recipient cell empty string")
    void testCheckRecipientCellEmpty() {
        assertFalse(sendingMessages.checkRecipientCell(""), 
            "Empty recipient cell should be invalid");
    }

    // =========================
    // MESSAGE HASH CREATION TESTS
    // =========================
    @Test
    @DisplayName("Create message hash with single word")
    void testCreateMessageHashSingleWord() {
        sendingMessages.messageID = "1234567890";
        sendingMessages.messageText = "Hello";
        SendingMessages.totalMessages = 5;
        
        String hash = sendingMessages.createMessageHash();
        assertEquals("12:5:HELLOHELLO", hash, 
            "Hash should be constructed from message ID prefix, total messages, and repeated word");
    }

    @Test
    @DisplayName("Create message hash with multiple words")
    void testCreateMessageHashMultipleWords() {
        sendingMessages.messageID = "9876543210";
        sendingMessages.messageText = "Hello World Test";
        SendingMessages.totalMessages = 3;
        
        String hash = sendingMessages.createMessageHash();
        assertEquals("98:3:HELLOTEST", hash, 
            "Hash should use first and last words");
    }

    @Test
    @DisplayName("Create message hash with extra spaces")
    void testCreateMessageHashExtraSpaces() {
        sendingMessages.messageID = "1111111111";
        sendingMessages.messageText = "  Message   with   spaces  ";
        SendingMessages.totalMessages = 1;
        
        String hash = sendingMessages.createMessageHash();
        assertEquals("11:1:MESSAGESPACES", hash, 
            "Hash should trim and split correctly");
    }

    @Test
    @DisplayName("Create message hash with zero total messages")
    void testCreateMessageHashZeroMessages() {
        sendingMessages.messageID = "5555555555";
        sendingMessages.messageText = "Test Message";
        SendingMessages.totalMessages = 0;
        
        String hash = sendingMessages.createMessageHash();
        assertEquals("55:0:TESTMESSAGE", hash, 
            "Hash should work with zero total messages");
    }

    @Test
    @DisplayName("Create message hash with large total messages count")
    void testCreateMessageHashLargeCount() {
        sendingMessages.messageID = "1234567890";
        sendingMessages.messageText = "Large Count";
        SendingMessages.totalMessages = 99999;
        
        String hash = sendingMessages.createMessageHash();
        assertEquals("12:99999:LARGECOUNT", hash, 
            "Hash should handle large message counts");
    }

    // =========================
    // STORE MESSAGE TESTS
    // =========================
    @Test
    @DisplayName("Store message method executes successfully")
    void testStoreMessage() {
        assertDoesNotThrow(() -> sendingMessages.storeMessage(), 
            "Store message should execute without throwing exception");
    }

    // =========================
    // PRINT MESSAGES TESTS
    // =========================
    @Test
    @DisplayName("Print messages method executes successfully")
    void testPrintMessages() {
        sendingMessages.messageID = "1234567890";
        sendingMessages.recipient = "+27123456789";
        sendingMessages.messageText = "Test Message";
        sendingMessages.messageHash = "12:1:TESTMESSAGE";
        
        assertDoesNotThrow(() -> sendingMessages.printMessages(), 
            "Print messages should execute without throwing exception");
    }

    // =========================
    // RETURN TOTAL MESSAGES TESTS
    // =========================
    @Test
    @DisplayName("Return total messages with zero messages")
    void testReturnTotalMessagesZero() {
        SendingMessages.totalMessages = 0;
        assertEquals(0, sendingMessages.returnTotalMessages(), 
            "Should return 0 for total messages");
    }

    @Test
    @DisplayName("Return total messages with multiple messages")
    void testReturnTotalMessagesMultiple() {
        SendingMessages.totalMessages = 5;
        assertEquals(5, sendingMessages.returnTotalMessages(), 
            "Should return correct total messages count");
    }

    @Test
    @DisplayName("Return total messages with large count")
    void testReturnTotalMessagesLarge() {
        SendingMessages.totalMessages = 1000;
        assertEquals(1000, sendingMessages.returnTotalMessages(), 
            "Should return large message count correctly");
    }

    // =========================
    // INTEGRATION TESTS
    // =========================
    @Test
    @DisplayName("Message hash format consistency")
    void testMessageHashFormatConsistency() {
        sendingMessages.messageID = "1234567890";
        sendingMessages.messageText = "Consistent Format Test";
        SendingMessages.totalMessages = 2;
        
        String hash = sendingMessages.createMessageHash();
        String[] parts = hash.split(":");
        
        assertEquals(3, parts.length, 
            "Hash should have 3 parts separated by colons");
        assertEquals("12", parts[0], 
            "First part should be first 2 chars of message ID");
        assertEquals("2", parts[1], 
            "Second part should be total messages");
        assertEquals("CONSISTENTTEST", parts[2], 
            "Third part should be first and last word in uppercase");
    }

    @Test
    @DisplayName("Recipient validation before message creation")
    void testRecipientValidationWorkflow() {
        String validRecipient = "+27123456789";
        String invalidRecipient = "0123456789";
        
        assertTrue(sendingMessages.checkRecipientCell(validRecipient), 
            "Valid recipient should be accepted");
        assertFalse(sendingMessages.checkRecipientCell(invalidRecipient), 
            "Invalid recipient should be rejected");
    }

    @Test
    @DisplayName("Message ID and recipient validation workflow")
    void testMessageValidationWorkflow() {
        String validMessageID = "1234567890";
        String invalidMessageID = "12345678901";
        String validRecipient = "+27123456789";
        
        assertTrue(sendingMessages.checkMessageID(validMessageID), 
            "Valid message ID should be accepted");
        assertFalse(sendingMessages.checkMessageID(invalidMessageID), 
            "Invalid message ID should be rejected");
        assertTrue(sendingMessages.checkRecipientCell(validRecipient), 
            "Valid recipient should be accepted");
    }

    @Test
    @DisplayName("Total messages increments correctly")
    void testTotalMessagesIncrement() {
        SendingMessages.totalMessages = 0;
        assertEquals(0, SendingMessages.totalMessages);
        
        SendingMessages.totalMessages++;
        assertEquals(1, SendingMessages.totalMessages);
        
        SendingMessages.totalMessages += 5;
        assertEquals(6, SendingMessages.totalMessages);
    }
}
