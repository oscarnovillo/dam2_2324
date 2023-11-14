package domain.servicios;

import lombok.SneakyThrows;

import java.security.MessageDigest;

public class HashPassword {

    @SneakyThrows
    public String hashPassword(String args) {

        MessageDigest md = MessageDigest.getInstance("SHA3-512");

        byte[] hashBytes = md.digest(args.getBytes());
        return bytesToHex(hashBytes);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
