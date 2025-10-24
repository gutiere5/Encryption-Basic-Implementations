package DiffieHellmanAlgorithm;

import java.math.BigInteger;

public class DHBruteForce {

    private static BigInteger modPow(BigInteger base, BigInteger exp, BigInteger mod) {
        return base.modPow(exp, mod);
    }

    public static void main(String[] args) {
        BigInteger P = new BigInteger("23");
        BigInteger G = new BigInteger("5");
        BigInteger a = new BigInteger("4");  // secret a
        BigInteger b = new BigInteger("3");  // secret b

        int maxExp = 99999;
        long maxMillis = 920_000;

        BigInteger X = modPow(G, a, P);
        BigInteger Y = modPow(G, b, P);
        BigInteger Kab = modPow(Y, a, P);

        System.out.println("=== Setup ===");
        System.out.println("P=" + P + "  G=" + G);
        System.out.println("a = " + a + "  b = " + b);
        System.out.println("Kab = " + Kab);
        System.out.println();

        // BRUTE FORCE BOTH
        System.out.println("=== Brute-force BOTH a and b ===");
        long attempts = 0;
        boolean found = false;
        BigInteger foundA = null, foundB = null;

        long startBoth = System.nanoTime();
        outer:
        for (int at = 1; at <= maxExp; at++) {
            for (int bt = 1; bt <= maxExp; bt++) {
                attempts++;

                BigInteger X_candidate = modPow(G, BigInteger.valueOf(at), P);
                BigInteger Y_candidate = modPow(G, BigInteger.valueOf(bt), P);

                if (X_candidate.equals(X) && Y_candidate.equals(Y)) {
                    found = true;
                    foundA = BigInteger.valueOf(at);
                    foundB = BigInteger.valueOf(bt);
                    break outer;
                }
            }
        }
        double elapsedBothMs = (System.nanoTime() - startBoth) / 1_000_000.0;
        if (found) {
            System.out.println("Found! a = " + foundA + " , b = " + foundB);
            System.out.println("Attempts = " + attempts + " , Time = " + String.format("%.3f ms", elapsedBothMs));
            BigInteger verify = modPow(Y, foundA, P);
            System.out.println("Kab = " + Kab);
        } else {
            System.out.println("Not found within limits. Attempts = " + attempts + " , Time = " + String.format("%.3f ms", elapsedBothMs));
        }
        System.out.println();

        // BRUTE FORCE WHEN A IS KNOWN
        System.out.println("=== Brute-force b when a is KNOWN ===");
        attempts = 0;
        found = false;
        BigInteger knownA = a;
        BigInteger recoveredB = null;
        BigInteger baseWhenAKnown = modPow(G, knownA, P);

        long startKnownA = System.nanoTime();
        for (int bt = 1; bt <= maxExp; bt++) {
            attempts++;
            BigInteger candidate = modPow(baseWhenAKnown, BigInteger.valueOf(bt), P);
            if (candidate.equals(Kab)) {
                found = true;
                recoveredB = BigInteger.valueOf(bt);
                break;
            }
        }
        double elapsedKnownAMs = (System.nanoTime() - startKnownA) / 1_000_000.0;
        if (found) {
            System.out.println("Known a = " + knownA + " -> Recovered b = " + recoveredB);
            System.out.println("Attempts = " + attempts + " , Time = " + String.format("%.3f ms", elapsedKnownAMs));
            System.out.println("Kab = " + Kab);
        } else {
            System.out.println("Did not recover b within limits. Attempts = " + attempts + " , Time = " + String.format("%.3f ms", elapsedKnownAMs));
        }
        System.out.println();

    }
}
