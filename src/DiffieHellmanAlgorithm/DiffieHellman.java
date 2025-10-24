package DiffieHellmanAlgorithm;

import java.math.BigInteger;

public class DiffieHellman {

    private static BigInteger power(BigInteger base, BigInteger exponent, BigInteger modulus) {
        /*
         * Reference Link:
         * https://en.wikipedia.org/wiki/Modular_exponentiation
         */
        return base.modPow(exponent, modulus);
    }

    static void main() {
        /*
         * Diffie Hellman Algorithm
         * Reference Link:
         * https://en.wikipedia.org/wiki/Diffie%E2%80%93Hellman_key_exchange
         *
         */
        BigInteger P, G, a, b, X, Y, Ka, Kb;


        // --- 1. Agreement on Public Values P and G ---
        // Both the persons will be agreed upon the
        // public keys g and p

        // A prime number p is taken for the modulus arithmetic
        P = new BigInteger("11");

        System.out.println("The value of modulus P:" + P);

        // A primitive root for p, g is taken as the base
        G = new BigInteger("2");
        System.out.println("The value of base G:" + G);

        // --- 2. Alice's Steps ---
        // Alice chooses the private key 'a'
        a = new BigInteger("6");
        System.out.println("\nAlice's private key 'a': " + a);

        // Alice calculates her public key X = G^a mod P
        X = power(G, a, P);
        System.out.println("Alice's public key 'X': " + X);

        // --- 3. Bob's Steps ---

        // Bob chooses the private key 'b'
        b = new BigInteger("8");
        System.out.println("\nBob's private key 'b': " + b);

        // Bob calculates his public key Y = G^b mod P
        Y = power(G, b, P);
        System.out.println("Bob's public key 'Y': " + Y);

        // --- 4. Key Exchange and Calculation of Shared Secret ---

        // Alice calculates the shared secret Ka = Y^a mod P (where Y is Bob's public key)
        Ka = power(Y, a, P); // Y^a mod P

        // Bob calculates the shared secret Kb = X^b mod P (where X is Alice's public key)
        Kb = power(X, b, P); // X^b mod P

        System.out.println("\nSecret key for the Alice is: " + Ka);
        System.out.println("Secret key for the Bob is: " + Kb);

        if (Ka.equals(Kb)) {
            System.out.println("Keys match");
        } else {
            System.out.println("Keys do not match");

        }
    }
}