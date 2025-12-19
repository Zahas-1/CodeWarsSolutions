import java.util.Arrays;

public class OddOrEven {

    /**
     * Determines whether the sum of the integers in the array is odd or even.
     *
     * @param array an array of integers
     * @return "even" if the sum is even, otherwise "odd"
     */
    public static String oddOrEven(int[] array) {

        // If the array is empty, treat it as [0]
        // The sum of [0] is 0, which is even
        if (array.length == 0) {
            return "even";
        }

        // Use Java Streams to sum all elements in the array
        int sum = Arrays.stream(array)
                .sum();

        // Use modulo (%) to check parity:
        // - sum % 2 == 0 → even
        // - sum % 2 != 0 → odd
        return sum % 2 == 0 ? "even" : "odd";
    }
}
