package day5;

public class SimpleCRC {

    // Performs XOR on two binary characters
    static char xor(char a, char b) {
        return (a == b) ? '0' : '1';
    }

    // Performs binary division and returns the remainder
    static String divide(String dividend, String divisor) {
        int len = divisor.length();

        String temp = dividend.substring(0, len);

        while (len < dividend.length()) {
            if (temp.charAt(0) == '1') {
                // Perform XOR and bring down next bit
                temp = xorString(temp, divisor) + dividend.charAt(len);
            } else {
                // XOR with all 0s (no change)
                temp = xorString(temp, "0".repeat(len)) + dividend.charAt(len);
            }
            temp = temp.substring(1); // remove leftmost bit
            len++;
        }

        // Final step after last bit
        if (temp.charAt(0) == '1') {
            temp = xorString(temp, divisor);
        } else {
            temp = xorString(temp, "0".repeat(len));
        }

        return temp.substring(1); // return remainder (CRC bits)
    }

    // XORs two binary strings
    static String xorString(String a, String b) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < a.length(); i++) {
            result.append(xor(a.charAt(i), b.charAt(i)));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Step 1: Original data and generator
        String data = "111011";             // Message
        String generator = "110";        // Generator polynomial (degree 3)

        // Step 2: Append zeros (length of generator - 1)
        String appendedData = data + "00";

        // Step 3: Get CRC remainder
        String crc = divide(appendedData, generator);
        System.out.println("CRC bits: " + crc);

        // Step 4: Final codeword to transmit
        String codeword = data + crc;
        System.out.println("Transmitted codeword: " + codeword);

        // Step 5: Simulate receiver side check
        String received = codeword; // could change this to simulate error
        String check = divide(received, generator);

        // Step 6: If all zeros => no error
        if (Integer.parseInt(check) == 0) {
            System.out.println("No error detected.");
        } else {
            System.out.println("Error detected in received data!");
        }
    }
}
