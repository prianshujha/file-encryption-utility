
# File Encryption Utility

## Overview

The **File Encryption Utility** is a robust and secure command-line tool built in **Java** for encrypting and decrypting files using **AES** (Advanced Encryption Standard). It provides a simple interface to securely encrypt and decrypt files while allowing the storage and management of cryptographic keys.

This project utilizes Java's `javax.crypto` package and provides a set of utility methods for:

- AES key generation
- File encryption and decryption
- Key storage in files
- Base64 encoding/decoding

The utility is designed to be lightweight and highly customizable, ideal for developers looking for a straightforward encryption tool with strong security.

---

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
  - [Encryption](#encryption)
  - [Decryption](#decryption)
- [Key Management](#key-management)
- [Error Handling](#error-handling)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **AES-128** encryption for robust security.
- Simple command-line interface for file encryption/decryption.
- Automatic key generation and storage.
- Key loading and usage from a stored file.
- Support for both text and binary file encryption.

---

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java 8** or later
- **Maven** (for building and running the project)
- Basic familiarity with **command-line interfaces**.

---

## Installation

1. **Clone the repository:**

```bash
git clone https://github.com/prianshujha/file-encryption-utility.git
cd file-encryption-utility
```

2. **Build the project using Maven:**

```bash
mvn clean install
```

---

## Usage

### Command-Line Interface

The project provides a **CommandLineInterface** for encrypting and decrypting files. Here's how you can use the utility.

### Encryption

To encrypt a file, you need:

1. **Input file** to encrypt.
2. **Output file** where the encrypted data will be saved.
3. **Key file** that contains the encryption key.

Run the following command:

```bash
mvn exec:java -Dexec.args="encrypt input.txt encrypted_output.txt keyfile.key"
```

This command will:

- Encrypt `input.txt` using the AES algorithm.
- Save the encrypted data to `encrypted_output.txt`.
- Use the encryption key stored in `keyfile.key`.

### Decryption

To decrypt a file, you need:

1. **Encrypted file**.
2. **Output file** where the decrypted data will be saved.
3. **Key file** containing the decryption key.

Run the following command:

```bash
mvn exec:java -Dexec.args="decrypt encrypted_output.txt decrypted_output.txt keyfile.key"
```

This command will:

- Decrypt `encrypted_output.txt` using the key from `keyfile.key`.
- Save the decrypted content to `decrypted_output.txt`.

---

## Key Management

### Key Generation

The AES encryption algorithm requires a secret key. This utility can generate a secure key for you:

1. **Generate a key** using the following code:

```java
SecretKey secretKey = CryptoUtils.generateSecretKey();
```

2. **Save the key** to a file:

```java
File keyFile = new File("keyfile.key");
CryptoUtils.saveSecretKey(secretKey, keyFile);
```

### Key Storage

> **⚠️ CAUTION:** 
>
>The generated secret key is stored as a binary file (`keyfile.key`). This file **must** be protected and stored securely. Anyone with access to this file can decrypt your data, compromising the security of your encrypted files. Ensure restricted access and consider additional safeguards like environment variables or a secure vault for production use.

---

## Error Handling

### Invalid Key Length

If the key length is not valid for AES, you will encounter an error. AES supports key sizes of 128, 192, and 256 bits. If you are generating the key manually, make sure the length is compatible with AES.

### File Not Found

Ensure that the paths to the input files and key files are correct. If the file does not exist, an error will be thrown.

### Invalid Command-Line Arguments

If you forget to specify any arguments or provide incorrect ones, the CLI will display a usage message:

```text
Usage: java CommandLineInterface <encrypt/decrypt> <inputFile> <outputFile> <keyFile>
```

---

## Contributing

We welcome contributions to this project! If you find bugs, have feature requests, or want to improve the project, please feel free to open an issue or submit a pull request.

- Fork the repository
- Create a new branch
- Make your changes
- Submit a pull request with a detailed description of your changes

---

## License

This project is licensed under the MIT License.

---

## Contact

If you have any questions, feel free to open an issue on the [GitHub repository](https://github.com/prianshujha/file-encryption-utility) or contact the maintainers directly.
