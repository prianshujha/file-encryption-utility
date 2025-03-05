package com.fileencryption.cli;

import com.fileencryption.core.EncryptionService;
import com.fileencryption.core.DecryptionService;
import com.fileencryption.core.CryptoUtils;

import javax.crypto.SecretKey;
import java.io.File;
import java.util.Scanner;

public class CommandLineInterface {

    public static void main(String[] args) {
        // Check if the user has provided the correct number of arguments
        if (args.length < 4) {
            System.out.println("Usage: java CommandLineInterface <encrypt/decrypt> <inputFile> <outputFile> <keyFile>");
            return;
        }

        String action = args[0];  // either "encrypt" or "decrypt"
        File inputFile = new File(args[1]);
        File outputFile = new File(args[2]);
        File keyFile = new File(args[3]);

        try {
            // Load the secret key from the key file
            SecretKey secretKey = CryptoUtils.loadSecretKey(keyFile);

            // Perform encryption or decryption based on user input
            if ("encrypt".equalsIgnoreCase(action)) {
                EncryptionService.encryptFile(inputFile, outputFile, secretKey);
                System.out.println("File encrypted successfully. Encrypted file: " + outputFile.getAbsolutePath());
            } else if ("decrypt".equalsIgnoreCase(action)) {
                DecryptionService.decryptFile(inputFile, outputFile, secretKey);
                System.out.println("File decrypted successfully. Decrypted file: " + outputFile.getAbsolutePath());
            } else {
                System.out.println("Invalid action. Use 'encrypt' or 'decrypt'.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
