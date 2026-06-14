/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author HP
 */
public class SendingMessages {
    
    public SendingMessages() {
    }
      @Test
    public void testCheckRecipientCellValid() {

        SendingMessages msg =
                new SendingMessages();

        assertTrue(
                msg.checkRecipientCell(
                        "+27838968976"));
    }
  @Test
    public void testCheckRecipientCellInvalid() {

        SendingMessages msg =
                new SendingMessages();

        
    }

    @Test
    public void testCheckMessageIDValid() {

        SendingMessages msg =
                new SendingMessages();

       
    }}
