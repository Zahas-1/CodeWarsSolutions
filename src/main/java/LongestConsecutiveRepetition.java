import java.util.Arrays;

public class LongestConsecutiveRepetition {
    public static Object[] longestRepetition(String s) {

        // If the string is null or empty, return the required default value.
        // In an empty string there are no character repetitions.
        if (s == null || s.isEmpty()) {
            return new Object[]{"", 0};
        }

        // Split the string into consecutive runs of identical characters.
        // The regex "(?<=(.))(?!\\1)" is a zero-width split point meaning:
        //
        //  (?<=(.))   — Look behind for ANY character and capture it.
        //               This simply means “we are right after some character X”.
        //
        //  (?!\\1)    — Look ahead and assert that the NEXT character
        //               is NOT the same as the captured one.
        //
        // This effectively splits at boundaries where the character changes.
        // Example:
        //   "aaabbcaaa" splits into: ["aaa", "bb", "c", "aaa"]
        //
        // Arrays.stream(...) produces a stream of these "runs".
        //
        String longest =
                Arrays.stream(s.split("(?<=(.))(?!\\1)"))
                        // Find the longest run based on string length.
                        // Comparator subtracts lengths to determine which is larger.
                        .max((a, b) -> a.length() - b.length())
                        // If something unexpected happens, default to "".
                        .orElse("");

        // The longest run is a string like "aaa", "bb", etc.
        // So:
        //   - longest.charAt(0) gives the repeated character
        //   - longest.length() gives how many times it repeats
        return new Object[]{String.valueOf(longest.charAt(0)), longest.length()};
    }
}
