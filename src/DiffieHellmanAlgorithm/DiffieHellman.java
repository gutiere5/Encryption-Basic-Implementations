package DiffieHellmanAlgorithm;

public class DiffieHellman {

    private static long power(long base, long exponent, long modulus) {
        /*
        * Reference Link:
        * https://en.wikipedia.org/wiki/Modular_exponentiation
        */

        long result = 1;
        base %= modulus;

        while (exponent > 0) {
            // If exponent is odd, multiply base with result
            if ((exponent & 1) == 1) {
                result = (result * base) % modulus;
            }

            // Exponent must be even now
            exponent = exponent >> 1; // exponent = exponent / 2
            base = (base * base) % modulus; // Change base to base^2
        }
        return result;
    }

    static void main() {
        /*
         * Diffie Hellman Algorithm
         * Reference Link:
         * https://en.wikipedia.org/wiki/Diffie%E2%80%93Hellman_key_exchange
         *
         */

        long p, g, a, b, A, B, x, y;

        // Both the persons will be agreed upon the
        // public keys g and p

        // A prime number p is taken for the modulus arithmetic
        p = 23;
        System.out.println("The value of modulus p:" + p);

        // A primitive root for p, g is taken as the base
        g = 5;
        System.out.println("The value of base g:" + g);

        // Alice will choose the private key a
        // a is the chosen private key
        a = 4;
        System.out.println("The private key a for Alice:" + a);

        // Gets the generated key from Alice to send to Bob
        A = power(g, a, p);

        // Bob will choose the private key b
        // b is the chosen private key
        b = 3;
        System.out.println("The private key b for Bob:" + b);

        // Gets the generated key from Bob to send to Alice
        B = power(g, b, p);

        // Generating the secret key after the exchange of key
        x = power(B, a, p); // Secret key for Alice
        y = power(A, b, p); // Secret key for Bob

        System.out.println("Secret key for the Alice is: " + x);
        System.out.println("Secret key for the Bob is: " + y);
    }

}
