package com.project.passwordgenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Password Generator started");

        // Step 1: Ask for password length with input validation
        int length = -1;
        while (length <= 0) {
            System.out.println("Enter the desired length of your password (positive integer):");
            if (scanner.hasNextInt()) {
                length = scanner.nextInt();
                if (length <= 0) {
                    System.out.println("Please enter a positive integer.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Step 2: Ask what type of characters the user wants
        boolean hasUpperCase = askForCharacterType(scanner, "UpperCase Letters");
        boolean hasLowerCase = askForCharacterType(scanner, "LowerCase Letters");
        boolean hasNumbers = askForCharacterType(scanner, "Numbers");
        boolean hasSymbols = askForCharacterType(scanner, "Symbols");

        // Step 3: Generate the password
        String password = generatePassword(length, hasUpperCase, hasLowerCase, hasNumbers, hasSymbols);
        System.out.println("Your generated password is: " + password);

        // Step 4: Check Strength of the password
        String strength = checkPasswordStrength(password);
        System.out.println("Password Strength: " + strength);

        // Step 5: Ask for confirmation before saving to a file
        System.out.println("Do you want to save the password to a file? (yes/no)");
        String saveResponse = scanner.next();
        if (saveResponse.equalsIgnoreCase("yes")) {
            try {
                savePasswordToFile(password);
                System.out.println("Password saved to file successfully.");
            } catch (IOException e) {
                System.out.println("Error saving the password.");
            }
        }

        // Step 6: Automatically copy password to clipboard
        autoCopyToClipboard(password);
        System.out.println("✅ Password copied to clipboard!");

        // Optional: Show Password History from the file
        try {
            showPasswordHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close(); // Close scanner to prevent resource leak
    }

    // Method to ask user if they want a specific type of character in the password
    public static boolean askForCharacterType(Scanner scanner, String characterType) {
        System.out.println("Do you want " + characterType + "? (yes/no)");
        return scanner.next().equalsIgnoreCase("yes");
    }

    // Method to generate a password
    public static String generatePassword(int length, boolean hasUpperCase, boolean hasLowerCase, boolean hasNumbers,
                                           boolean hasSymbols) {
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#%^&*()-_=+[]{}|;:',.<>?";

        String allCharacters = "";
        if (hasUpperCase) allCharacters += uppercase;
        if (hasLowerCase) allCharacters += lowercase;
        if (hasNumbers) allCharacters += numbers;
        if (hasSymbols) allCharacters += symbols;

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(index));
        }
        return password.toString();
    }

    // Method to check password strength based on basic rules
    public static String checkPasswordStrength(String password) {
        int length = password.length();
        boolean hasUpper = false, hasLower = false, hasNumber = false, hasSymbol = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasNumber = true;
            else hasSymbol = true;
        }

        if (length >= 12 && hasUpper && hasLower && hasNumber && hasSymbol) {
            return "Strong ✅";
        } else if (length >= 8 && ((hasUpper && hasLower) || (hasNumber && hasLower))) {
            return "Medium ⚠️";
        } else {
            return "Weak ❌";
        }
    }

    // Method to save the password to a file with a timestamp
    public static void savePasswordToFile(String password) throws IOException {
        FileWriter writer = new FileWriter("password.txt", true);
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        String timestamp = format.format(new Date());

        writer.write("[" + timestamp + "]\n");
        writer.write("Password: " + password + "\n\n");
        writer.close();
    }

    // Method to show password history from the saved file
    public static void showPasswordHistory() throws IOException {
        File file = new File("password.txt");
        if (!file.exists() || file.length() == 0) {
            System.out.println("No saved passwords found.");
            return;
        }

        FileReader reader = new FileReader(file);
        int i;
        while ((i = reader.read()) != -1) {
            System.out.print((char) i);
        }
        reader.close();
    }

    // Method to automatically copy password to clipboard
    public static void autoCopyToClipboard(String password) {
        StringSelection stringSelection = new StringSelection(password);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
} 