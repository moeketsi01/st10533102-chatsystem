package ChatSystemTest;
import com.mycompany.chatsystem.ChatSystem;
import com.mycompany.chatsystem.SendingMessages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatSystemTest {

    // =========================
    // USERNAME TESTS
    // =========================

    @Test
    public void testUsernameValid() {

        assertTrue(
                ChatSystem.checkUserName(
                        "kyl_1"));
    }
    @Test
    public void testInvalidUsername() {

        assertFalse(
                ChatSystem.checkUserName("kyle!!!!"));
    }
@Test
    public void testUsernameInvalid() {

        assertFalse(
                ChatSystem.checkUserName(
                        "kyle123"));
    }
    // =========================
    // PASSWORD TESTS
    // =========================
    @Test
    public void testValidPassword() {

        assertTrue(
                ChatSystem.checkPasswordComplexity(
                        "Ch&&sec@ke99!"));
    }

    @Test
    public void testInvalidPassword() {

        assertFalse(
                ChatSystem.checkPasswordComplexity(
                        "password"));
    }

    // =========================
    // PHONE NUMBER TESTS
    // =========================
    @Test
    public void testValidPhoneNumber() {

        assertTrue(
                ChatSystem.checkCellPhoneNumber(
                        "+27838968976"));
    }

    @Test
    public void testInvalidPhoneNumber() {

        assertFalse(
                ChatSystem.checkCellPhoneNumber(
                        "08966553"));
    }
     @Test
    public void testPhoneNumberInvalid() {

        assertFalse(
                ChatSystem.checkCellPhoneNumber(
                        "0838968976"));
    }

    // =========================
    // MESSAGE ID TEST
    // =========================
    @Test
    public void testMessageID() {

        SendingMessages message =
                new SendingMessages();

        assertTrue(
                message.checkMessageID(
                        "1234567890"));
    }

    // =========================
    // RECIPIENT CELL TEST
    // =========================
    @Test
    public void testRecipientCell() {

        SendingMessages message =
                new SendingMessages();

        assertTrue(
                message.checkRecipientCell(
                        "+27838968976"));
    }

    // =========================
    // MESSAGE LENGTH TEST
    // =========================
    @Test
    public void testMessageLength() {

        String text =
                "Hello world";

        assertTrue(
                text.length() < 250);
    }
}