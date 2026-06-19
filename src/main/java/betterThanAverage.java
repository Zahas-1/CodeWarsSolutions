/*
There was a test in your class and you passed it. Congratulations!
But you're an ambitious person. You want to know if you're better than the average student in your class.
You receive an array with your peers' test scores. Now calculate the average and compare your score!
Return true if you're better, else false!

Note:
Your points are not included in the array of your class's points. Do not forget them when calculating the average score!
 */

import java.util.Arrays;

public class betterThanAverage {
    public static boolean betterThanAverage(int[] classPoints, int yourPoints) {
        return Arrays.stream(classPoints)
                // Converts the array to an IntStream and calculates the arithmetic mean
                .average()
                // Safely handles an empty array by returning 0.0 instead of throwing an exception
                .orElse(0.0)
                // Checks if your score is strictly greater than the computed average
                < yourPoints;
    }
}
