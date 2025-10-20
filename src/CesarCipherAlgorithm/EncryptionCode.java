package CesarCipherAlgorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class EncryptionCode {
    static void main() throws IOException {
        Scanner in = new Scanner(System.in);
        try {
            String[] messageWordArray = Files.lines(Path.of("src/CesarCipherAlgorithm/message.txt"))
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .toArray(String[]::new);
            System.out.println("Reading File Complete");

            System.out.println("Enter an integer value from 1 to 6 for encryption key");
            int userKey = in.nextInt();
            while (userKey > 6) {
                System.out.println("Integer value must be from 1 to 6");
                userKey = in.nextInt();
            }

            System.out.println("Running Caesar Cipher with key: " + userKey);
            boolean result = runCeaserCipher(messageWordArray, userKey);

            if (result) {
                System.out.println("Success: Decrypted message matches original message");
            } else {
                System.out.println("Failure: Decrypted message does not match original message");
            }
        }
        catch (Exception e) {
            System.out.println("There was an issue: " + e.getMessage());
        }
    }

    public static boolean runCeaserCipher(String[] message, int userKey) {
        System.out.println("File size in words: " + message.length);

        char[] messageChars = String.join(" ", message).toCharArray();
        char[] encryptedMessage = encrypt(messageChars, userKey);
        char[] decryptedMessage = decrypt(encryptedMessage, userKey);

        System.out.println("Encryption and Decryption Complete");
        System.out.println("User Message Sample: " + new String(Arrays.copyOfRange(messageChars, 0, Math.min(100, messageChars.length))));
        System.out.println("Encrypted Message Sample: " + new String(Arrays.copyOfRange(encryptedMessage, 0, Math.min(100, encryptedMessage.length))));
        System.out.println("Decrypted Message Sample: " + new String(Arrays.copyOfRange(decryptedMessage, 0, Math.min(100, decryptedMessage.length))));

        return Arrays.equals(messageChars, decryptedMessage);
    }

    private static char[] encrypt(char[] message, int key) {return cipherMessage(message, key, true);}

    private static char[] decrypt(char[] message, int key) {return cipherMessage(message, key, false);}

    private static char[] cipherMessage(char[] message, int key, boolean encrypt) {
        long startTime = System.nanoTime();
        char[] resultMessage = new char[message.length];
        int shift = encrypt ? key : -key;
        String cipherType = encrypt ? "Encryption" : "Decryption";

        for (int i = 0; i < message.length; i++) {
            char newChar = (char) (message[i] + shift);
            resultMessage[i] = newChar;
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(cipherType + " in nanoseconds: " + duration + " or " + duration / 1_000_000 + " milliseconds");
        return resultMessage;
    }
}