package com.fileencryption;

import com.fileencryption.core.CryptoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private static final String INPUT_FILE = "input.txt";
    private static final String ENCRYPTED_FILE = "encrypted.txt";
    private static final String DECRYPTED_FILE = "decrypted.txt";
    private static final String KEY_FILE = "secretKey.key";

    @BeforeEach
    public void setup() throws Exception {
        // Create test input file
        File inputFile = new File(INPUT_FILE);
        if (!inputFile.exists()) {
            inputFile.createNewFile();
        }

        // Write some test data to input file
        java.nio.file.Files.write(inputFile.toPath(), "This is a test input file.".getBytes());

        // Generate AES secret key and save to file
        SecretKey secretKey = CryptoUtils.generateSecretKey();
        File keyFile = new File(KEY_FILE);
        CryptoUtils.saveSecretKey(secretKey, keyFile);
    }

    @Test
    public void testEncryptionAndDecryption() throws Exception {
        // Load key from file
        File keyFile = new File(KEY_FILE);
        SecretKey secretKey = CryptoUtils.loadSecretKey(keyFile);

        // Encrypt the input file
        File inputFile = new File(INPUT_FILE);
        File encryptedFile = new File(ENCRYPTED_FILE);
        CryptoUtils.encryptFile(inputFile, encryptedFile, secretKey);

        // Decrypt the encrypted file
        File decryptedFile = new File(DECRYPTED_FILE);
        CryptoUtils.decryptFile(encryptedFile, decryptedFile, secretKey);

        // Check if the decrypted file content is the same as the original input file content
        byte[] originalContent = java.nio.file.Files.readAllBytes(inputFile.toPath());
        byte[] decryptedContent = java.nio.file.Files.readAllBytes(decryptedFile.toPath());
        assertArrayEquals(originalContent, decryptedContent, "Decrypted content does not match the original input!");
    }

    @Test
    public void testKeyFileGeneration() throws Exception {
        // Check if key file exists
        File keyFile = new File(KEY_FILE);
        assertTrue(keyFile.exists(), "Key file should exist!");

        // Load the key and check if it is not null
        SecretKey secretKey = CryptoUtils.loadSecretKey(keyFile);
        assertNotNull(secretKey, "Secret key should not be null!");
    }
}
