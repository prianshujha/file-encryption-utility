package com.fileencryption.core;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class DecryptionService {

    private static final String ALGORITHM = "AES";

    // Method to decrypt a file
    public static void decryptFile(File inputFile, File outputFile, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Read the encrypted content of the file
        byte[] encryptedContent = Files.readAllBytes(inputFile.toPath());
        byte[] decryptedContent = cipher.doFinal(encryptedContent);

        // Write the decrypted content to the output file
        Files.write(outputFile.toPath(), decryptedContent);
    }
}
