/*
Write a function, persistence, that takes in a positive parameter num and
returns its multiplicative persistence, which is the number of times you
must multiply the digits in num until you reach a single digit.

For example (Input --> Output):

39 --> 3 (because 3*9 = 27, 2*7 = 14, 1*4 = 4 and 4 has only one digit,
there are 3 multiplications)

999 --> 4 (because 9*9*9 = 729, 7*2*9 = 126, 1*2*6 = 12, and finally 1*2 = 2,
there are 4 multiplications)

4 --> 0 (because 4 is already a one-digit number, there is no multiplication)

 */

public class Persistence {
    public static int persistence(long n) {
        int count = 0;
        while(n >= 10){
            int sum = 1;
            char[] nums = String.valueOf(n).toCharArray();
        for(char num : nums) {
            sum = sum * Character.getNumericValue(num);
            }
        n = sum;
        count++;
        }

        return count;
    }

    public static int persistenceStream(long n){
        // Base case: if n is already one digit, we’re done
        if (n < 10) return 0;

        // Multiply all digits using streams
        long product = String.valueOf(n)
                .chars() // stream of int values representing each char
                .map(Character::getNumericValue) // convert chars → digits
                .reduce(1, Math::multiplyExact); // multiply them together

        // 1 recursive step + however many needed for the next product
        return 1 + persistence(product);
    }

    public static void main(String[] args) {
        System.out.println(persistence(39));
        System.out.println(persistenceStream(39));   // 3
        System.out.println(persistenceStream(999));  // 4
        System.out.println(persistenceStream(4));    // 0
        System.out.println(persistenceStream(25));   // 2
    }
}
