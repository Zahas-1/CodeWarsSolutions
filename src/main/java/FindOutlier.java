/*
You are given an array (which will have a length of at least 3,
but could be very large) containing integers. The array is either
entirely comprised of odd integers or entirely comprised of even
integers except for a single integer N.

Write a method that takes the array as an argument and returns this "outlier" N.

Examples
[2, 4, 0, 100, 4, 11, 2602, 36] -->  11 (the only odd number)

[160, 3, 1719, 19, 11, 13, -21] --> 160 (the only even number)
 */

import java.util.Arrays;
import java.util.stream.Collectors;

public class FindOutlier {

    static int find(int[] ints) {

        return Arrays.stream(ints)          // Convert the int[] array into an IntStream
                .boxed()                         // Convert IntStream → Stream<Integer>
                // (needed for grouping/collecting operations)
                .collect(                       // Collect the stream into a Map<Boolean, List<Integer>>
                        Collectors.partitioningBy(
                                n -> n % 2 == 0          // Predicate:
                                // true  → even numbers
                                // false → odd numbers
                        )
                )

                .values()                       // Get the collection of Lists from the map
                // Result: two lists → [evens, odds]
                .stream()                       // Convert the collection of lists into a Stream<List<Integer>>
                .filter(list -> list.size() == 1)
                // Keep only the list that contains exactly one element
                // This list represents the "outlier"

                .findFirst()                    // Get the first (and only) such list
                .get()                          // Extract the List<Integer> from the Optional
                .get(0);                        // Return the single element inside that list (the outlier)
    }

    public static void main(String[] args) {
        int[] nums = {200,20,333333333,42,46,4,6,8};
        System.out.println(find(nums));
    }
}




