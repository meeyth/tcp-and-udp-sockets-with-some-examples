package day5;

public class SimpleHammingCode {

    public static void main(String[] args) {

        // Step 1: Hardcoded 4 data bits (d3 d5 d6 d7 positions in 1-based index)
        int d3 = 1;
        int d5 = 0;
        int d6 = 1;
        int d7 = 1;

        // Step 2: Calculate redundant bits using parity check logic
        int r1 = d3 ^ d5 ^ d7;        // covers bits 1,3,5,7
        int r2 = d3 ^ d6 ^ d7;        // covers bits 2,3,6,7
        int r4 = d5 ^ d6 ^ d7;        // covers bits 4,5,6,7

        // Step 3: Form Hamming Code (positions 1 to 7)
        int[] hammingCode = new int[8];  // index 0 unused for simplicity

        hammingCode[1] = r1;
        hammingCode[2] = r2;
        hammingCode[3] = d3;
        hammingCode[4] = r4;
        hammingCode[5] = d5;
        hammingCode[6] = d6;
        hammingCode[7] = d7;

        System.out.print("Hamming code: ");
        for (int i = 1; i <= 7; i++) {
            System.out.print(hammingCode[i]);
        }

        // Step 4: Introduce an error (flip bit at position 5)
        hammingCode[5] ^= 1;

        System.out.print("\nReceived code with error: ");
        for (int i = 1; i <= 7; i++) {
            System.out.print(hammingCode[i]);
        }

        // Step 5: Recalculate parity to detect error
        int c1 = hammingCode[1] ^ hammingCode[3] ^ hammingCode[5] ^ hammingCode[7];
        int c2 = hammingCode[2] ^ hammingCode[3] ^ hammingCode[6] ^ hammingCode[7];
        int c4 = hammingCode[4] ^ hammingCode[5] ^ hammingCode[6] ^ hammingCode[7];

        int errorPosition = c4 * 4 + c2 * 2 + c1 * 1;
//                          1        0        1
        if (errorPosition == 0) {
            System.out.println("\nNo error detected.");
        } else {
            System.out.println("\nError detected at position: " + errorPosition);
            // Correct the error
            hammingCode[errorPosition] ^= 1;

            System.out.print("Corrected code: ");
            for (int i = 1; i <= 7; i++) {
                System.out.print(hammingCode[i]);
            }
        }
    }
}
