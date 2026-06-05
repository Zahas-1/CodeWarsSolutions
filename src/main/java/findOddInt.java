/*
Given an array of integers, find the one that appears an odd number of times.

There will always be only one integer that appears an odd number of times.

Examples
[7] should return 7, because it occurs 1 time (which is odd).
[0] should return 0, because it occurs 1 time (which is odd).
[1,1,2] should return 2, because it occurs 1 time (which is odd).
[0,1,0,1,0] should return 0, because it occurs 3 times (which is odd).
[1,2,2,3,3,3,4,3,3,3,2,2,1] should return 4, because it appears 1 time (which is odd).

 */

public class findOddInt {

    public static int findIt(int[] a) {
        // Start with 0 because 0 ^ x = x
        int result = 0;

        // XOR every number in the array
        for (int num : a) {
            result ^= num;
        }

        /*
         * Numbers that appear an even number of times
         * cancel themselves out because:
         * x ^ x = 0
         *
         * Example:
         * [1, 1, 2, 2, 3]
         *
         * 0 ^ 1 ^ 1 ^ 2 ^ 2 ^ 3
         * = 0 ^ 0 ^ 0 ^ 3
         * = 3
         *
         * The remaining value is the number that
         * appears an odd number of times.
         */
        return result;
    }
}