/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package com.mycompany.prog5121_part1;

import java.util.Random;
import java.util.Scanner;

public class PROG5121_Part1 {

    private static final Random RANDOM = new Random();

    // Generates a random, ten-digit message ID.
    private static String generateMessageID() {
        long number = 1_000_000_000L
                + (long) (RANDOM.nextDouble() * 9_000_000_000L);

        return String.valueOf(number);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Login loginApp = new Login();

        System.out.println("=== USER REGISTRATION ===");

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Cell Phone Number (e.g., +27123456789): ");
        String cellNumber = scanner.nextLine();

        String regResult = loginApp.registerUser(
                username, password, cellNumber, firstName, lastName);

        System.out.println("\n" + regResult + "\n");

        if (regResult.equals("Password successfully captured.")) {

            System.out.println("=== USER LOGIN ===");

            System.out.print("Enter Username: ");
            String loginUser = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPass = scanner.nextLine();

            boolean isAuthenticated =
                    loginApp.loginUser(loginUser, loginPass);

            System.out.println(loginApp.returnLoginStatus(isAuthenticated));

            if (isAuthenticated) {
                System.out.println("Welcome to QuickChat.");

                boolean running = true;
                int nextMessageNumber = 0;

                while (running) {
                    System.out.println("\n=== QUICKCHAT MENU ===");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Choose an option: ");

                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1": {
                            System.out.print(
                                    "How many messages would you like to send? ");

                            int numberOfMessages;

                            try {
                                numberOfMessages =
                                        Integer.parseInt(scanner.nextLine());

                                if (numberOfMessages <= 0) {
                                    System.out.println(
                                            "Please enter a number greater than 0.");
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(
                                        "Please enter a valid number.");
                                break;
                            }

                            int processed = 0;

                            while (processed < numberOfMessages) {
                                System.out.println(
                                        "\nMessage " + (processed + 1));

                                System.out.print(
                                        "Enter recipient cell number (+27...): ");
                                String recipient = scanner.nextLine();

                                System.out.print("Enter your message: ");
                                String messageText = scanner.nextLine();

                                Message message = new Message(
                                        generateMessageID(),
                                        nextMessageNumber,
                                        recipient,
                                        messageText);

                                if (!message.checkMessageID()) {
                                    System.out.println("Invalid message ID.");
                                    continue;
                                }

                                String recipientStatus =
                                        message.checkRecipientCell();

                                if (!recipientStatus.equals(
                                        "Cell phone number successfully captured.")) {
                                    System.out.println(recipientStatus);
                                    continue;
                                }

                                String lengthStatus =
                                        message.checkMessageLength();

                                if (!lengthStatus.equals(
                                        "Message ready to send.")) {
                                    System.out.println(lengthStatus);
                                    continue;
                                }

                                System.out.println(
                                        "Message Hash: "
                                        + message.createMessageHash());

                                System.out.println("\n1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println(
                                        "3) Store Message to send later");
                                System.out.print("Choose an option: ");

                                int action;

                                try {
                                    action = Integer.parseInt(
                                            scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid choice.");
                                    continue;
                                }

                                String actionResult =
                                        message.SentMessage(action);

                                System.out.println(actionResult);

                                
                                 if (action == 1) {
                                    System.out.println(
                                        "Message ID: " + message.getMessageID());
                                    System.out.println(
                                        "Message Hash: " + message.createMessageHash());
                                    System.out.println(
                                        "Recipient: " + recipient);
                                    System.out.println(
                                        "Message: " + messageText);
                                }


                                if (action == 1 || action == 2 || action == 3) {
                                    processed++;
                                    nextMessageNumber++;
                                }
                            }

                            Message viewer = new Message(
                                    "0000000000", 0, "", "");

                            System.out.println(
                                    "Total messages sent: "
                                    + viewer.returnTotalMessagess());
                            break;
                        }

                        case "2":
    System.out.println("Coming Soon.");
    break;

                        case "3":
                            System.out.println(
                                    "Thank you for using QuickChat.");
                            running = false;
                            break;

                        default:
                            System.out.println(
                                    "Invalid option. Please choose 1, 2, or 3.");
                    }
                }
            }
        }

        scanner.close();
    }
}
