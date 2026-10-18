/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121_part1;

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
}

    

