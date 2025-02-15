// simple asf hwid tool for kitty client

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {

        try {
            String hwid = getHWID();
            System.out.println("HWID: " + hwid);
            System.out.println("Make a ticket and give them the HWID");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getHWID() throws NoSuchAlgorithmException {
        String main = System.getenv("PROCESSOR_IDENTIFIER") +
                System.getenv("COMPUTERNAME") +
                System.getProperty("user.name").trim();
        byte[] bytes = main.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] md5 = messageDigest.digest(bytes);

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < md5.length; i++) {
            s.append(Integer.toHexString((md5[i] & 0xFF) | 0x300).substring(0, 3));
            if (i != md5.length - 1) {
                s.append("/");
            }
        }
        return s.toString();
    }
}
