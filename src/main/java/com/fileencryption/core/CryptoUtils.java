package com.fileencryption.core;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Base64;

public class CryptoUtils {

    private static final String ALGORITHM = "AES";

    // Method to generate a SecretKey for AES encryption
    public static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128); // AES key size (128 bits)
        return keyGenerator.generateKey();
    }

    // Method to save the SecretKey to a file
    public static void saveSecretKey(SecretKey secretKey, File keyFile) throws Exception {
        byte[] encodedKey = secretKey.getEncoded();
        Files.write(keyFile.toPath(), encodedKey);
    }

    // Method to load the SecretKey from a file
    public static SecretKey loadSecretKey(File keyFile) throws Exception {
        byte[] encodedKey = Files.readAllBytes(keyFile.toPath());
        return new SecretKeySpec(encodedKey, ALGORITHM);
    }

    // Method to encrypt data from a file using the SecretKey
    public static void encryptFile(File inputFile, File outputFile, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] encryptedBytes = cipher.doFinal(inputBytes);

            outputStream.write(encryptedBytes);
        }
    }

    // Method to decrypt data from a file using the SecretKey
    public static void decryptFile(File inputFile, File outputFile, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] decryptedBytes = cipher.doFinal(inputBytes);

            outputStream.write(decryptedBytes);
        }
    }

    // Method to convert a byte array to a Base64 encoded string
    public static String encodeToBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    // Method to decode a Base64 encoded string back to a byte array
    public static byte[] decodeFromBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    // Example of using the CryptoUtils class for encryption and decryption
    public static void main(String[] args) {
        try {
            // Generate a new AES Secret Key
            SecretKey secretKey = generateSecretKey();
            File keyFile = new File("secretKey.key");

            // Save the key to a file
            saveSecretKey(secretKey, keyFile);

            // Load the key from the file
            SecretKey loadedKey = loadSecretKey(keyFile);

            // Encrypt a file
            File inputFile = new File("input.txt");
            File encryptedFile = new File("encrypted.txt");
            encryptFile(inputFile, encryptedFile, loadedKey);

            // Decrypt the file
            File decryptedFile = new File("decrypted.txt");
            decryptFile(encryptedFile, decryptedFile, loadedKey);

            System.out.println("Encryption and Decryption completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
