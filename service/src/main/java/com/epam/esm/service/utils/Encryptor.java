package com.epam.esm.service.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Encryptor {

    /**
     * Hash password string.
     *
     * @param passwordPlaintext the password plaintext
     * @return the string
     */
    public static String hashPassword(String passwordPlaintext) {
        String hashedPassword = BCrypt.hashpw(passwordPlaintext, BCrypt.gensalt());
        return (hashedPassword);
    }

    /**
     * Check password boolean.
     *
     * @param passwordPlaintext the password plaintext
     * @param storedHash        the stored hash
     * @return the boolean
     */
    public static boolean checkPassword(String passwordPlaintext, String storedHash) {
        boolean passwordVerified;
        if (null == storedHash || !storedHash.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hash provided for comparison");
        }
        passwordVerified = BCrypt.checkpw(passwordPlaintext, storedHash);
        return (passwordVerified);
    }
}
