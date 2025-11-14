/*
Simple, given a string of words, return the length of the shortest word(s).
String will never be empty and you do not need to account for different data types.
 */

import java.util.Arrays;

public class ShortestWord {
    public static int findShort(String s) {
        return Arrays.stream(s.split(" "))   //Split the input string into an array of words, then create a stream of those words
                .mapToInt(String::length) //Convert each word into its length (turns Stream<String> into IntStream)
                .min()                    //Find the smallest length in the stream
                .orElse(0);               //Return the result, or 0 if the stream is empty (safe fallback)
    }

    public static void main(String[] args) {
        System.out.println(findShort("bitcoin take over the world maybe who knows perhaps")); // Output: 3
        System.out.println(findShort("turns out random test cases are easier than writing out basic ones")); // Output: 3
        System.out.println(findShort("Let's travel abroad shall we")); // Output: 2
    }
}
