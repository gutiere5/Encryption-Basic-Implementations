package BasicEncryptionExample;

import java.util.Scanner;

public class EncryptionCode {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Type something to be encrypted or the encrypted message.");
        String userMessage = in.nextLine();

        System.out.println("Enter an integer value from 1 to 6 for encryption key");
        int userKey = in.nextInt();

        while (userKey > 6) {
            System.out.println("Integer value must be from 1 to 6");
            userKey = in.nextInt();
        }

        System.out.println("What is the secret secondary key?");
        int secondKey = in.nextInt();

        String message = userMessage;
        int key = userKey;
        char [] chars = message.toCharArray();
        for (char i :  chars) {
            i += key;
            System.out.print(i);
        }

    }



}
