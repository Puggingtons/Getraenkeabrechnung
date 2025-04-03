package de.dhbw.karlsruhe.getraenkeabrechnung;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.util.regex.Pattern;

public class Password {

    private final String passwordString;
    private String hashedPassword;
    private String salt;

    public Password(String password) {
        this.passwordString = password;
    }

    public Boolean isValid(Optional<String> pattern) {

        if (pattern.isEmpty()) {
            return false;
        }

        Pattern compiledPasswordPattern = Pattern.compile(pattern.get());

        return compiledPasswordPattern.matcher(this.passwordString).matches();
    }

    public String hashPassword() {
        try {
            SecureRandom random = new SecureRandom();
            byte[] saltBytes = new byte[16];
            random.nextBytes(saltBytes);
            this.salt = Base64.getEncoder().encodeToString(saltBytes);

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(saltBytes);
            md.update(passwordString.getBytes());

            byte[] hashedBytes = md.digest();
            this.hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

            return this.hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing passoword", e);
        }
    }

    public static boolean verifyPassword(String plainPassword, String storedHash, String storedSalt) {
        try {
            byte[] saltBytes = Base64.getDecoder().decode(storedSalt);

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(saltBytes);
            md.update(plainPassword.getBytes());

            byte[] hashedBytes = md.digest();
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

            return hashedPassword.equals(storedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error verifying password", e);
        }
    }

    public String getSalt() {
        return this.salt;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

}