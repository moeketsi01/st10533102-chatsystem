package com.mycompany.chatsystem;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ChatSystem {

    // =========================
    // USER DETAILS
    // =========================
    static String storedUsername;
    static String storedPassword;
    static String firstName;
    static String lastName;

    // =========================
    // MESSAGE DETAILS
    // =========================
    static int totalMessages = 0;

    String messageID;
    String recipient;
    String messageText;
    String messageHash;

    Scanner input = new Scanner(System.in);

    // =========================
    // USERNAME VALIDATION
    // =========================
    public static boolean checkUserName(String username) {

        return username.contains("_")
                && username.length() <= 5;
    }

    // =========================
    // PASSWORD VALIDATION
    // =========================
    public static boolean checkPasswordComplexity(
            String password) {

        String regex =
                "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$";

        return Pattern.matches(regex, password);
    }

    // =========================
    // SA CELL VALIDATION
    // =========================
    public static boolean checkCellPhoneNumber(
            String phone) {

        String regex = "^\\+27[0-9]{9}$";

        return Pattern.matches(regex, phone);
    }

    // =========================
    // REGISTER USER
    // =========================
    public static String registerUser(
            String username,
            String password,
            String phone) {

        if (!checkUserName(username)) {

            return "Username is not correctly formatted; "
                    + "please ensure that your username "
                    + "contains an underscore and is "
                    + "no more than five characters "
                    + "in length.";
        }

        if (!checkPasswordComplexity(password)) {

            return "Password is not correctly formatted; "
                    + "please ensure that the password "
                    + "contains at least eight characters, "
                    + "a capital letter, a number, "
                    + "and a special character.";
        }

        if (!checkCellPhoneNumber(phone)) {

            return "Cell phone number incorrectly formatted "
                    + "or does not contain international code.";
        }

        storedUsername = username;
        storedPassword = password;

        return "User has been registered successfully.";
    }

    // =========================
    // LOGIN USER
    // =========================
    public static boolean loginUser(
            String username,
            String password) {

        return username.equals(storedUsername)
                && password.equals(storedPassword);
    }

    // =========================
    // LOGIN STATUS
    // =========================
    public static String returnLoginStatus(
            boolean loginSuccess) {

        if (loginSuccess) {

            return "Welcome "
                    + firstName
                    + lastName
                    + ", "
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, "
                + "please try again.";
    }

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

     System.out.println("\n===== QUICKCHAT MENU =====");
System.out.println("1. Send Message");
System.out.println("2. Display Report");
System.out.println("3. Search By Recipient");
System.out.println("4. Search By Message ID");
System.out.println("5. Delete By Hash");
System.out.println("6. Display Longest Message");
System.out.println("7. Quit");

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
                    "Message exceeds 250 characters"
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

    // =========================
    // MAIN METHOD
    // =========================
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // CREATE OBJECT
        SendingMessages messageObject =
        new SendingMessages();

        // =========================
        // REGISTER
        // =========================
        System.out.println("=== Register ===");

        System.out.print("Enter username: ");
        String username =
                input.nextLine();

        System.out.print("Enter password: ");
        String password =
                input.nextLine();

        
        System.out.print(
                "Enter SA phone number (+27...): ");

        String phone =
                input.nextLine();

        System.out.print(
                "Enter first name: ");

        firstName =
                input.nextLine();

        System.out.print(
                "Enter last name: ");

        lastName =
                input.nextLine();

        String registrationMessage =
                registerUser(
                        username,
                        password,
                        phone);

        System.out.println(
                registrationMessage);

        // =========================
        // LOGIN
        // =========================
        System.out.println("\n=== Login ===");

        System.out.print(
                "Enter username: ");

        String loginUsername =
                input.nextLine();

        System.out.print(
                "Enter password: ");

        String loginPassword =
                input.nextLine();

        boolean loginSuccess =
                loginUser(
                        loginUsername,
                        loginPassword);

        System.out.println(
                returnLoginStatus(
                        loginSuccess));

        // =========================
        // QUICKCHAT MENU
        // =========================
        if (loginSuccess) {

            System.out.println(
                    "\nWelcome to ChatApp");

            System.out.print(
                    "How many messages would "
                    + "you like to send? ");

            int numberOfMessages =
                    input.nextInt();

            input.nextLine();

            for (int i = 0;
                    i < numberOfMessages;
                    i++) {

                System.out.println(
                        "\nMessage "
                        + (i + 1));

                messageObject.captureMessage();
            }

            // MENU LOOP
int option = 0;

while (option != 7) {

    System.out.println("\n===== QUICKCHAT MENU =====");
    System.out.println("1. Send Message");
    System.out.println("2. Display Report");
    System.out.println("3. Search By Recipient");
    System.out.println("4. Search By Message ID");
    System.out.println("5. Delete Message By Hash");
    System.out.println("6. Display Longest Message");
    System.out.println("7. Quit");

    System.out.print("Choose option: ");
    option = input.nextInt();
    input.nextLine();


              switch (option) {

    case 1:

        messageObject.captureMessage();

        break;

    case 2:

        messageObject.displayReport();

        break;

    case 3:

        System.out.print(
                "Enter recipient number: ");

        String recipientSearch =
                input.nextLine();

        messageObject.searchRecipient(
                recipientSearch);

        break;

    case 4:

        System.out.print(
                "Enter message ID: ");

        String idSearch =
                input.nextLine();

        messageObject.searchByMessageID(
                idSearch);

        break;

    case 5:

        System.out.print(
                "Enter message hash: ");

        String hashSearch =
                input.nextLine();

        messageObject.deleteMessageByHash(
                hashSearch);

        break;

    case 6:

        System.out.println(
                "Longest Message: "
                + messageObject.displayLongestMessage());

        break;

    case 7:

        System.out.println(
                "Exiting QuickChat...");

        break;

    default:

        System.out.println(
                "Invalid option.");
}
}
                }
            
        }
}
