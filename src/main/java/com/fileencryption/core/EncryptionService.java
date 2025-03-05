package com.fileencryption.core;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptionService {

    private static final String ALGORITHM = "AES";

    // Method to encrypt a file
    public static void encryptFile(File inputFile, File outputFile, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Read the content of the file
        byte[] fileContent = Files.readAllBytes(inputFile.toPath());
        byte[] encryptedContent = cipher.doFinal(fileContent);

        // Write the encrypted content to the output file
        Files.write(outputFile.toPath(), encryptedContent);
    }
}
