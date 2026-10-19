/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121_part1; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author petro
 */


public class MessageTest {
    @Test
    public void testValidMessageID() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hello tonight");

        assertTrue(message.checkMessageID());
    }

    @Test
    public void testInvalidMessageID() {
        Message message = new Message(
                "123", 0, "+27718693002", "Hello tonight");

        assertFalse(message.checkMessageID());
    }

    @Test
    public void testValidRecipient() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hello tonight");

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell());
    }

    @Test
    public void testInvalidRecipient() {
        Message message = new Message(
                "1234567890", 0, "08575975889", "Hello tonight");

        assertEquals(
                "Cell phone number incorrectly formatted or does not contain international code.",
                message.checkRecipientCell());
    }

    @Test
    public void testValidMessageLength() {
        Message message = new Message(
                "0012345678", 0, "+27718693002", "Hello tonight");

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength());
    }

    @Test
    public void testMessageTooLong() {
        String longText = "A".repeat(260);

        Message message = new Message(
                "1234567890", 0, "+27718693002", longText);

        assertEquals(
                "Message exceeds 250 characters by 10; please reduce the size.",
                message.checkMessageLength());
    }

    @Test
    public void testMessageHash() {
        Message message = new Message(
                "0012345678", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("00:0:HITONIGHT",
                message.createMessageHash());
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hello tonight");

        assertEquals(
                "Message successfully sent",
                message.SentMessage(1));
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hello tonight");

        assertEquals(
                "Press 0 to delete the message",
                message.SentMessage(2));
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hello tonight");

        assertEquals(
                "Message successfully stored",
                message.SentMessage(3));
    }
}


