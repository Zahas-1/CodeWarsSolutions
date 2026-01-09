import java.util.Arrays;

/*
Implement a function that receives two IPv4 addresses, and returns the number of
addresses between them (including the first one, excluding the last one).

All inputs will be valid IPv4 addresses in the form of strings. The last
address will always be greater than the first one.

Examples
* With input "10.0.0.0", "10.0.0.50"  => return   50
* With input "10.0.0.0", "10.0.1.0"   => return  256
* With input "20.0.0.10", "20.0.1.0"  => return  246
 */
public class CountIPAddresses {
    public static long ipsBetween(String start, String end) {
        // We subtract the numerical values of the two IPs.
        // IPv4 addresses are base-256 numbers.
        return ipToLong(end) - ipToLong(start);
    }

    private static long ipToLong(String ip) {
        // Split the IP by the dot literal.
        String[] octets = ip.split("\\.");
        long result = 0;

        for (int i = 0; i < 4; i++) {
            /*
             * result << 8: Shifts the current value 8 bits to the left.
             * In binary, this is equivalent to multiplying by 2^8 (256).
             * This "makes room" for the next 8-bit octet.
             */
            result = (result << 8) + Integer.parseInt(octets[i]);
        }

        /*
         * Why 'long' instead of 'int'?
         * An IPv4 address is 32 bits. A Java 'int' is also 32 bits, but it
         * is SIGNED (ranges from -2,147,483,648 to 2,147,483,647).
         * If an IP starts with 128 or higher, the 32nd bit is 1, which
         * Java would interpret as a negative number, causing incorrect subtraction.
         * A 'long' provides 64 bits, safely handling the full 32-bit unsigned range.
         */
        return result;
    }

    private static long ipToLongStreams(String ip) {
        // 1. split("\\."): Breaks the string into a String array ["192", "168", "1", "1"]
        return Arrays.stream(ip.split("\\."))
                // 2. mapToLong: Converts each String octet into a numerical Long.
                // We use Long to prevent signed 32-bit integer overflow.
                .mapToLong(Long::parseLong)
                /*
                 * 3. reduce(identity, accumulator):
                 * This combines all elements into a single result.
                 *
                 * identity (0L): The starting value of our calculation.
                 * accumulator: A lambda that takes the 'total' so far and the 'nextOctet'.
                 *
                 * Logic: (total << 8) + octet
                 * This shifts the total 8 bits left (same as multiplying by 256)
                 * to make room for the next octet in the sequence.
                 */
                .reduce(0L, (total, octet) -> (total << 8) + octet);
    }
}