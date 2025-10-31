/*
You will return the digitSum(a) + digitsum(b).

For example:
solve(29) = 11. If we take 15 + 14 = 29 and digitSum = 1 + 5 + 1 + 4 = 11.
There is no larger outcome.
n will not exceed 10e10.
 */

public class SimpleSumOfPairs {

    /**
     * Returns the maximum possible value of digitSum(a) + digitSum(b)
     * such that a + b = n.
     *
     * @param n a positive long (n <= 1e10)
     * @return the maximum digit sum achievable
     */
    public static int solve(long n) {
        // Step 1: Find the largest number consisting only of 9's that is <= n
        // Example: if n = 1000 → 999
        // if n = 29 → 19
        long a = largestAllNinesBelow(n);
        long b = n - a;

        // Step 2: Calculate digit sums
        return digitSum(a) + digitSum(b);
    }

    /**
     * Computes the digit sum of a number.
     * For example: digitSum(123) = 6.
     */
    private static int digitSum(long num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    /**
     * Finds the largest number made only of 9's that is <= n.
     * Example:
     *   n = 29  → 19
     *   n = 1000 → 999
     *   n = 9876 → 999
     */
    private static long largestAllNinesBelow(long n) {
        long result = 0;
        long place = 1;

        // Build the number from right to left
        while (n >= 10) {
            result += 9 * place;
            place *= 10;
            n /= 10;
        }

        return result;
    }

    // Quick test
    public static void main(String[] args) {
        System.out.println(solve(29));     // 11
        System.out.println(solve(999));    // 54 (since 999 = 999 + 0 → 9+9+9+0 = 27? Wait, no! 999+0=999 so max pair is 999+0 → 27)
        System.out.println(solve(1000));   // 27 (999 + 1)
        System.out.println(solve(12345));  // should work for big n
    }
}

