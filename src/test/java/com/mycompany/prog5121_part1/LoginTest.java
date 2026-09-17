/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog5121_part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author petro
 */
public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

    // ==========================================
    // SECTION 1: assertEquals Tests
    // ==========================================

    @Test
    public void testUsernameCorrectlyFormattedMessage() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "ntuli");
        boolean isLogged = login.loginUser("kyl_1", "Ch&&sec@ke99!");
         String expected = "Welcome Kyle, ntuli it is great to see you again.";
         String actual = login.returnLoginStatus(isLogged);
        assertEquals(expected, actual);
    }

    @Test
    public void testUsernameIncorrectlyFormattedMessage() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "ntuli");
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordMeetsComplexityMessage() {
        String expected = "Password successfully captured.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "ntuli");
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordDoesNotMeetComplexityMessage() {
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = login.registerUser("kyl_1", "password", "+27838968976", "Kyle", "ntuli");
        assertEquals(expected, actual);
    }

    @Test
    public void testCellPhoneCorrectlyFormattedMessage() {
        String expected = "Password successfully captured.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "ntuli");
        assertEquals(expected, actual);
    }

    @Test
    public void testCellPhoneIncorrectlyFormattedMessage() {
        String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "ntuli");
        assertEquals(expected, actual);
    }

    // ==========================================
    // SECTION 2: assertTrue / assertFalse Tests
    // ==========================================

    @Test
    public void testLoginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "ntuli");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "ntuli");
        assertFalse(login.loginUser("kyl_1", "wrongPassword"));
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}