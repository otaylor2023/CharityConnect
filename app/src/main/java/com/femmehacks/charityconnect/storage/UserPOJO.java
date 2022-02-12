package com.femmehacks.charityconnect.storage;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserPOJO {

    private String email;
    private String hashedPassword;
    private String displayName;

    public UserPOJO() {}

    public UserPOJO(UserPOJO userPOJO) {
        this.email = userPOJO.email;
        this.hashedPassword = userPOJO.hashedPassword;
        this.displayName = userPOJO.displayName;
    }

    public boolean checkPassword(String unhashedPassword) {
        try {
            String encoded = encodeSHA256(unhashedPassword);
            return (hashedPassword.equals(encoded));
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }




    public static String encodeSHA256(String unhashed) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(unhashed.getBytes());
        byte[] digest = md.digest();

        return Base64.encodeToString(digest, Base64.DEFAULT);
    }
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
