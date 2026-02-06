public class sumOfMultiples {
    public static long sumMul(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("n and m must be positive integers");
        }
        long sum = 0;
        // Start at n, increment by n, and stay below m exclusive
        for (int i = n; i < m; i += n) {
            sum += i;
        }
        return sum;
    }
}