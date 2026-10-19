/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121_part1;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author petro
 */

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
     private static final List<Message> sentMessages = new ArrayList<>();
    private static int totalMessages = 0;

    // Constructor: stores the details of one message
    public Message(String messageID, int messageNumber,
            String recipient, String messageText) {

        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
    }

    // Checks that the message ID contains no more than 10 digits
    public boolean checkMessageID() {
        return messageID != null
                && messageID.matches("\\d{1,10}");
    }

    // Checks the recipient's South African international number
    public String checkRecipientCell() {
        if (recipient != null
                && recipient.matches("\\+27\\d{9}")) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number incorrectly formatted or does not contain international code.";
    }

    // Creates the message hash
    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2
                || messageText == null || messageText.trim().isEmpty()) {
            return "";
        }

        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1]
                .replaceAll("[^a-zA-Z0-9]", "");

        messageHash = (messageID.substring(0, 2) + ":"
                + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();

        return messageHash;
    }
    public String checkMessageLength() {
        if (messageText == null) {
            return "Message is empty.";
        }

        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }

        int excess = messageText.length() - 250;

        return "Message exceeds 250 characters by " + excess
                + "; please reduce the size.";
    }
        // Processes the user's message choice

    // Processes the user's message choice
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                sentMessages.add(this);
                totalMessages++;
                return "Message successfully sent";

            case 2:
                return "Press 0 to delete the message";

            case 3:
                return storeMessage();

            default:
                return "Invalid choice";
        }
    }
    public String getMessageID() {
    return messageID;
}

    // Displays all messages sent during the program
public String printMessages() {
    StringBuilder output = new StringBuilder();

    for (Message message : sentMessages) {
        output.append("Message ID: ")
              .append(message.messageID)
              .append("\nMessage Hash: ")
              .append(message.messageHash)
              .append("\nRecipient: ")
              .append(message.recipient)
              .append("\nMessage: ")
              .append(message.messageText)
              .append("\n\n");
    }

    return output.toString();
}
// Returns the total number of messages sent
public int returnTotalMessagess() {
    return totalMessages;
}

    
    // Stores this message in a JSON file
    public String storeMessage() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Message> storedMessages = new ArrayList<>();

        java.io.File file = new java.io.File("messages.json");

        try {
            if (file.exists() && file.length() > 0) {
                try (java.io.FileReader reader =
                        new java.io.FileReader(file)) {
                    Message[] existing = gson.fromJson(
                            reader, Message[].class);

                    if (existing != null) {
                        storedMessages.addAll(
                                java.util.Arrays.asList(existing));
                    }
                }
            }

            storedMessages.add(this);

            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(storedMessages, writer);
            }

            return "Message successfully stored";
        } catch (IOException e) {
            return "Error storing message: " + e.getMessage();
        }
    }

    }


    

