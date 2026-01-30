/*
Given an array of integers.

Return an array, where the first element is the count of positives numbers and the second element is sum of negative numbers. 0 is neither positive nor negative.

If the input is an empty array or is null, return an empty array.

Example
For input [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15], you should return [10, -65].
 */

import java.util.Arrays;

public class countPositivesSumNegatives {

    /**
     * Iterative Approach (Single Pass)
     * Performance: O(n) - Efficient as it only loops through the array once.
     */
    public static int[] countPositivesSumNegatives(int[] input) {
        // Return empty array if input is null or has no elements as per requirements
        if (input == null || input.length == 0) return new int[0];

        int countPositives = 0;
        int sumNegatives = 0;

        // Enhanced for-loop to process each number in one single pass
        for (int i : input) {
            if (i > 0) {
                countPositives++; // Increment count for positive numbers
            } else if (i < 0) {
                sumNegatives += i; // Add negative numbers to the running total
            }
        }

        // Return results in a new array: [count, sum]
        return new int[] {countPositives, sumNegatives};
    }

    /**
     * Stream Approach (Two Passes)
     * Performance: O(2n) - Readable but iterates through the data twice.
     */
    public static int[] countPositivesSumNegativesStreams(int[] input) {
        // Validation check
        if (input == null || input.length == 0) {
            return new int[0];
        }

        int[] array = {0, 0};

        // First pass: Filter for positives and count them
        // .count() returns a long, so an (int) cast is required
        array[0] = (int) Arrays.stream(input)
                .filter(i -> i > 0)
                .count();

        // Second pass: Filter for negatives and calculate their sum
        // .sum() on an IntStream returns an int directly
        array[1] = Arrays.stream(input)
                .filter(i -> i < 0)
                .sum();

        return array;
    }
}