package processor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class SaltedHash {

    public String generatehash(String password, String salted) throws NoSuchAlgorithmException {
        String originalString = password + salted;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashbytes = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        String sha256Text = bytesToHex(hashbytes);
        return sha256Text;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String generateSalted() {
        Random random = new SecureRandom();
        int length = 64;

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int c = random.nextInt(62);
            if (c <= 9) {
                sb.append(String.valueOf(c));
            } else if (c < 36) {
                sb.append((char) ('a' + c - 10));
            } else {
                sb.append((char) ('A' + c - 36));
            }
        }

        return sb.toString();
    }
}
