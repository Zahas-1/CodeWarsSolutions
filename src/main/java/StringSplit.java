/*
Complete the solution so that it splits the string into strings of two characters in a list/array
(depending on the language you use). If the string contains an odd number of characters then it
should replace the missing second character of the final pair with an underscore ('_').

Examples:

* 'abc' =>  ['ab', 'c_']
* 'abcdef' => ['ab', 'cd', 'ef']
 */

public class StringSplit {
    public static String[] solution(String s) {

        // Special case:
        // Java's split() returns [""] when called on an empty string,
        // but this kata expects an empty array [].
        if (s.isEmpty()) {
            return new String[0];
        }

        // If the string length is odd, append an underscore so that
        // every chunk can contain exactly 2 characters.
        //
        // Example:
        // "abc"  -> "abc_"
        // "hello" -> "hello_"
        if (s.length() % 2 == 1) {
            s += "_";
        }

        /*
         * Split the string every 2 characters.
         *
         * Regex breakdown:
         *
         * (?<=\\G..)
         *
         * Java String:
         *   "\\G"
         * becomes
         *   "\G"
         * in the actual regex.
         *
         * Components:
         *
         * (?<= ...)
         *   Positive lookbehind.
         *   Checks what is immediately BEFORE the current position.
         *   It does not consume characters.
         *
         * \G
         *   "End of previous match" anchor.
         *   For split(), this effectively starts at the beginning of
         *   the string and then continues from each previous split point.
         *
         * ..
         *   Two characters ('.' means any single character).
         *
         * Together:
         *   (?<=\G..)
         *
         * Means:
         *   "Find a position that has exactly 2 characters immediately
         *    before it since the previous split point."
         *
         * Example:
         *
         *   "abcdef"
         *
         *   Positions found:
         *
         *   ab|cd|ef
         *     ^  ^
         *
         *   split() cuts at those positions and returns:
         *
         *   ["ab", "cd", "ef"]
         *
         * Example:
         *
         *   "abc_"
         *
         *   ab|c_
         *
         *   Returns:
         *
         *   ["ab", "c_"]
         */
        return s.split("(?<=\\G..)");
    }
}

