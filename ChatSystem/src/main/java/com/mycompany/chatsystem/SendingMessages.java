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
    // CHECK MESSAGE ID
    // =========================
    public boolean checkMessageID(String id) {

        if (id.length() <= 10) {

            System.out.println(
                    "Message ID successfully created.");

            return true;
        }

        System.out.println(
                "Message ID exceeds 10 characters.");

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
                words[words.length - 1]
                .toUpperCase();

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

                totalMessages++;

                return "Message sent";

            case 2:

                return "Press 0 to delete the message";

            case 3:

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

        // GENERATE RANDOM 10 DIGIT MESSAGE ID
        messageID = String.valueOf(
                1000000000L
                + (long)(Math.random()
                * 9000000000L));

        checkMessageID(messageID);

        // ENTER RECIPIENT
        System.out.print(
                "Enter recipient number (+27): ");

        recipient = input.nextLine();

        if (!checkRecipientCell(recipient)) {

            System.out.println(
                    "Cell phone number incorrectly formatted "
                    + "or does not contain international code.");

            return;
        }

        // ENTER MESSAGE
        System.out.print(
                "Enter your message: ");

        messageText = input.nextLine();

        // CHECK MESSAGE LENGTH
        if (messageText.length() > 250) {

            int extraCharacters =
                    messageText.length() - 250;

            System.out.println(
                    "Message exceeds 250 characters by "
                    + extraCharacters
                    + ", please reduce size.");

            return;
        }

        // CREATE HASH
        messageHash = createMessageHash();

        // DISPLAY SEND OPTION
        System.out.println(sentMessage());

        // DISPLAY MESSAGE DETAILS
        printMessages();

        // DISPLAY TOTAL
        System.out.println(
                "Total Messages Sent: "
                + returnTotalMessages());
    }
}