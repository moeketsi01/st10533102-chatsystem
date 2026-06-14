package com.mycompany.chatsystem;

import java.util.Scanner;

public class SendingMessages {

    static int totalMessages = 0;

    String messageID;
    String recipient;
    String messageText;
    String messageHash;

    Scanner input = new Scanner(System.in);

    // =========================
    // PART 3 ARRAYS
    // =========================
    private String[] sentMessages = new String[100];
    private String[] storedMessages = new String[100];
    private String[] disregardedMessages = new String[100];

    private String[] messageHashes = new String[100];
    private String[] messageIDs = new String[100];
    private String[] recipients = new String[100];

    private int sentCount = 0;
    private int storedCount = 0;
    private int disregardedCount = 0;

    // =========================
    // CHECK MESSAGE ID
    // =========================
    public boolean checkMessageID(String id) {

        if (id.length() <= 10) {

            System.out.println(
                    "Message successfully created.");

            return true;
        }

        System.out.println(
                "Message exceeds 10 characters.");

        return false;
    }

    // =========================
    // CHECK RECIPIENT NUMBER
    // =========================
    public boolean checkRecipientCell(String cell) {

        return cell.matches("^\\+27[0-9]{9}$");
    }

    // =========================
    // CREATE MESSAGE HASH
    // =========================
    public String createMessageHash() {

        String[] words =
                messageText.trim().split("\\s+");

        String firstWord =
                words[0].toUpperCase();

        String lastWord =
                words[words.length - 1].toUpperCase();

        return messageID.substring(0, 2)
                + ":"
                + totalMessages
                + ":"
                + firstWord
                + lastWord;
    }

    // =========================
    // STORE MESSAGE
    // =========================
    public void storeMessage() {

        System.out.println(
                "Message successfully stored.");
    }

    // =========================
    // SEND MESSAGE OPTION
    // =========================
    public String sentMessage() {

    System.out.println("\nChoose Option:");
System.out.println("1. Send Message");
System.out.println("2. Disregard Message");
System.out.println("3. Store Message");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {

            case 1:

                sentMessages[sentCount] = messageText;
                messageHashes[sentCount] = messageHash;
                messageIDs[sentCount] = messageID;
                recipients[sentCount] = recipient;

                sentCount++;
                totalMessages++;

                return "Message successfully sent.";

            case 2:

                disregardedMessages[disregardedCount]
                        = messageText;

                disregardedCount++;

                return "Press 0 to delete message.";

            case 3:

                storedMessages[storedCount]
                        = messageText;

                storedCount++;

                storeMessage();

                return "Message successfully stored.";

            default:

                return "Invalid option.";
        }
    }

    // =========================
    // PRINT MESSAGE DETAILS
    // =========================
    public void printMessages() {

        System.out.println(
                "\n===== MESSAGE DETAILS =====");

        System.out.println(
                "Message ID: "
                + messageID);

        System.out.println(
                "Message Hash: "
                + messageHash);

        System.out.println(
                "Recipient: "
                + recipient);

        System.out.println(
                "Message: "
                + messageText);
    }

    // =========================
    // RETURN TOTAL MESSAGES
    // =========================
    public int returnTotalMessages() {

        return totalMessages;
    }

    // =========================
    // CAPTURE MESSAGE
    // =========================
    public void captureMessage() {

        // Generate random message ID
        messageID = String.valueOf(
        1000000000L
        + (long)(Math.random()
        * 9000000000L));
        checkMessageID(messageID);

        // Recipient
        System.out.print(
                "Enter recipient number (+27): ");

        recipient = input.nextLine();

        if (!checkRecipientCell(recipient)) {

            System.out.println(
                    "Cell phone number incorrectly formatted or does not contain international code.");

            return;
        }

        // Message
        System.out.print(
                "Enter your message: ");

        messageText = input.nextLine();

        //  character limit
        if (messageText.length() > 250) {

            int extraCharacters =
                    messageText.length() - 250;

            System.out.println(
        "Message exceeds 250 characters by "
        + extraCharacters
        + ", please reduce size.");
        }

        // Create hash
        messageHash = createMessageHash();

        // Send / Store / Disregard
        System.out.println(
                sentMessage());

        // Print details
        printMessages();

        // Total sent
        System.out.println(
                "Total Messages Sent: "
                + returnTotalMessages());
    }

    // =========================
    // LONGEST MESSAGE
    // =========================
    public String displayLongestMessage() {

    String longest = "";

    for (int i = 0; i < sentCount; i++) {

        if (sentMessages[i] != null
                && sentMessages[i].length()
                > longest.length()) {

            longest = sentMessages[i];
        }
    }

    return longest;
}

    // =========================
    // SEARCH RECIPIENT
    // =========================
    public String searchRecipient(String number) {

        String result = "";

        for (int i = 0; i < sentCount; i++) {

            if (recipients[i] != null
                    && recipients[i].equals(number)) {

                result += sentMessages[i]
                        + "\n";
            }
        }

        return result;
    }
    
// =========================
// SEARCH BY RECIPIENT
// =========================
public void searchByRecipient(String number) {

    boolean found = false;

    for (int i = 0; i < sentCount; i++) {

        if (recipients[i] != null
                && recipients[i].equals(number)) {

            System.out.println(
                    "Recipient: "
                    + recipients[i]);

            System.out.println(
                    "Message: "
                    + sentMessages[i]);

            found = true;
        }
    }

    if (!found) {

        System.out.println(
                "No messages found for recipient.");
    }
}
    // =========================
    // DISPLAY REPORT
    // =========================
    public void displayReport() {

        System.out.println(
                "\n===== MESSAGE REPORT =====");

        for (int i = 0; i < sentCount; i++) {

        if (sentMessages[i] != null) {

            System.out.println(
                    "Message ID: "
                    + messageIDs[i]);

            System.out.println(
                    "Message Hash: "
                    + messageHashes[i]);

            System.out.println(
                    "Recipient: "
                    + recipients[i]);

            System.out.println(
                    "Message: "
                    + sentMessages[i]);

            System.out.println(
                    "--------------------------------");
        }
    
        }
    
}
// =========================
// SEARCH BY MESSAGE ID
// =========================
public void searchByMessageID(String id) {

    boolean found = false;

    for (int i = 0; i < sentCount; i++) {

        if (messageIDs[i] != null
                && messageIDs[i].equals(id)) {

            System.out.println(
                    "Message Found");

            System.out.println(
                    "Message ID: "
                    + messageIDs[i]);

            System.out.println(
                    "Recipient: "
                    + recipients[i]);

            System.out.println(
                    "Message: "
                    + sentMessages[i]);

            found = true;
        }
    }

    if (!found) {

        System.out.println(
                "Message ID not found.");
    }
}
// =========================
// DELETE BY HASH
// =========================
public void deleteMessageByHash(String hash) {

    boolean found = false;

    for (int i = 0; i < sentCount; i++) {

        if (messageHashes[i] != null
                && messageHashes[i].equals(hash)) {

            sentMessages[i] = null;
            recipients[i] = null;
            messageHashes[i] = null;
            messageIDs[i] = null;

            System.out.println(
                    "Message successfully deleted.");

            found = true;
        }
    }

    if (!found) {

        System.out.println(
                "Hash not found.");
    }
}

}
   
            
        
