import java.math.BigInteger;

public class EasyLine {

    /**
     * Computes the sum of squares of binomial coefficients on line n of Pascal's Triangle.
     *
     * Mathematical identity used:
     *    sum(C(n, k)^2 for k = 0..n) = C(2n, n)
     *
     * So we only need to compute the central binomial coefficient C(2n, n).
     *
     * @param n the line number (n >= 0)
     * @return the BigInteger value of C(2n, n)
     */
    public static BigInteger easyLine(int n) {
        return binomial(2 * n, n);
    }

    /**
     * Computes the binomial coefficient "n choose k" = C(n, k)
     * using an iterative multiplicative approach.
     *
     * Formula:
     *    C(n, k) = n! / (k! * (n - k)!)
     *
     * But instead of computing factorials (which is expensive and memory heavy),
     * we use a multiplicative method that simplifies step-by-step:
     *
     *    C(n, k) = (n * (n - 1) * (n - 2) * ... * (n - k + 1)) / (1 * 2 * 3 * ... * k)
     *
     * This avoids overflow and works perfectly with BigInteger for large n.
     *
     * @param n total number of elements
     * @param k number of elements to choose
     * @return the BigInteger value of C(n, k)
     */
    private static BigInteger binomial(int n, int k) {
        BigInteger result = BigInteger.ONE;

        // C(n, k) = C(n, n - k), so we can minimize the number of multiplications
        // by using the smaller of k and n - k.
        if (k > n - k) {
            k = n - k;
        }

        // Iteratively compute the coefficient:
        // Multiply by (n - i + 1), divide by i at each step.
        for (int i = 1; i <= k; i++) {
            result = result.multiply(BigInteger.valueOf(n - i + 1))
                    .divide(BigInteger.valueOf(i));
        }

        return result;
    }

    /**
     * Main method for quick testing.
     * Prints known examples to verify correctness.
     */
    public static void main(String[] args) {
        System.out.println(easyLine(0));  // Expected: 1
        System.out.println(easyLine(1));  // Expected: 2
        System.out.println(easyLine(4));  // Expected: 70
        System.out.println(easyLine(50)); // Expected: 100891344545564193334812497256
    }
}
