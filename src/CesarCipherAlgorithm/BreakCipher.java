package CesarCipherAlgorithm;

import java.util.Scanner;

public class BreakCipher {
    static void main() {
        Scanner in = new Scanner(System.in);

        System.out.println("Paste your encrypted text:");
        String cipherInput = in.nextLine();
        char[] cipher = cipherInput.toCharArray();

        long start = System.nanoTime();

        // Try every key from 1 to 6
        for (int k = 1; k <= 6; k++) {
            String attempt = new String(decrypt(cipher, k));
            System.out.println("Key " + k + ": " + attempt);
        }

        long duration = System.nanoTime() - start;

        System.out.println("\nTried all keys (1–6).");
        System.out.println("Total time: " + duration + " ns (" + duration / 1_000_000.0 + " ms)");
    }

    static char[] decrypt(char[] msg, int key) {
        char[] out = new char[msg.length];
        for (int i = 0; i < msg.length; i++) {
            out[i] = (char) (msg[i] - key);
        }
        return out;
    }
}