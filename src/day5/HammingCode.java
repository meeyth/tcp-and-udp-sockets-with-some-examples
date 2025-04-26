package day5;

import java.util.Scanner;

public class HammingCode {

    // Function to calculate the number of redundant bits required
    static int calculateRedundantBits(int m) {
        int r = 0;
        // Find number of redundant bits such that (m + r + 1) <= 2^r
        while (Math.pow(2, r) < (m + r + 1)) {
            r++;
        }
        return r;
    }

    // Function to position redundant bits at positions which are powers of 2
    static int[] insertRedundantBits(int[] dataBits, int r) {
        int m = dataBits.length;
        int[] hammingCode = new int[m + r];
        int j = 0, k = 0;

        // Place data bits and leave redundant bit places (1-indexed)
        for (int i = 1; i <= hammingCode.length; i++) {
            if (Math.pow(2, j) == i) {
                hammingCode[i - 1] = 0; // Initialize redundant bit to 0
                j++;
            } else {
                hammingCode[i - 1] = dataBits[k];
                k++;
            }
        }
        return hammingCode;
    }

    // Function to calculate parity bits and update the hamming code
    static void calculateParityBits(int[] hammingCode, int r) {
        int n = hammingCode.length;

        // Calculate parity for each redundant bit
        for (int i = 0; i < r; i++) {
            int position = (int) Math.pow(2, i);
            int parity = 0;

            // Check bits covered by the current redundant bit
            for (int j = 1; j <= n; j++) {
                if ((j & position) != 0) {
                    parity ^= hammingCode[j - 1];
                }
            }

            // Set parity bit value
            hammingCode[position - 1] = parity;
        }
    }

    // Function to detect and correct a single-bit error
    static void detectAndCorrectError(int[] receivedCode, int r) {
        int n = receivedCode.length;
        int errorPosition = 0;

        // Recalculate parity bits to find error
        for (int i = 0; i < r; i++) {
            int position = (int) Math.pow(2, i);
            int parity = 0;

            for (int j = 1; j <= n; j++) {
                if ((j & position) != 0) {
                    parity ^= receivedCode[j - 1];
                }
            }

            // If parity is wrong, add position to error position
            if (parity != 0) {
                errorPosition += position;
            }
        }

        if (errorPosition == 0) {
            System.out.println("No error detected in the received Hamming code.");
        } else {
            System.out.println("Error detected at position: " + errorPosition);
            // Correct the error by flipping the bit
            receivedCode[errorPosition - 1] ^= 1;
            System.out.println("Corrected Hamming code:");
            for (int bit : receivedCode) {
                System.out.print(bit + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the data bits
        System.out.print("Enter the number of data bits: ");
        int m = scanner.nextInt();
        int[] dataBits = new int[m];

        System.out.println("Enter the data bits:");
        for (int i = 0; i < m; i++) {
            dataBits[i] = scanner.nextInt();
        }

        // Calculate number of redundant bits
        int r = calculateRedundantBits(m);

        // Insert redundant bits into data bits
        int[] hammingCode = insertRedundantBits(dataBits, r);

        // Calculate parity bits
        calculateParityBits(hammingCode, r);

        // Display generated Hamming code
        System.out.println("Generated Hamming code:");
        for (int bit : hammingCode) {
            System.out.print(bit + " ");
        }
        System.out.println();

        // Simulate receiving the code (allow user to enter received bits)
        System.out.println("Enter the received Hamming code (with possible error):");
        int[] receivedCode = new int[hammingCode.length];
        for (int i = 0; i < receivedCode.length; i++) {
            receivedCode[i] = scanner.nextInt();
        }

        // Detect and correct error if any
        detectAndCorrectError(receivedCode, r);

        scanner.close();
    }
}
