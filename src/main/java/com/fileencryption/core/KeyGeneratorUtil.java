package com.fileencryption.core;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class KeyGeneratorUtil {

    public static void generateKey(String keyFilePath) throws NoSuchAlgorithmException, IOException {
        // Create a KeyGenerator for AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);  // Set the key size (256 bits)

        // Generate the secret key
        SecretKey secretKey = keyGen.generateKey();

        // Write the key to the specified file
        try (FileOutputStream keyFile = new FileOutputStream(keyFilePath)) {
            keyFile.write(secretKey.getEncoded());
        }
    }

    public static void main(String[] args) {
        try {
            String keyFilePath = "/workspaces/file-encryption-utility"; // Key file to save the generated key
            generateKey(keyFilePath);
            System.out.println("Key generated and saved to " + keyFilePath);
        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println("Error generating key: " + e.getMessage());
        }
    }
}
