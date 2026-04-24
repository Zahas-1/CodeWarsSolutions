import java.util.stream.IntStream;
/*
The examples below show you how to write function accum:

Examples:
accum("abcd") -> "A-Bb-Ccc-Dddd"
accum("RqaEzty") -> "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"
accum("cwAt") -> "C-Ww-Aaa-Tttt"

The parameter of accum is a string which includes only letters from a..z and A..Z.
 */
public class Mumbling {
    public static String accum(String s) {

        // Split the string into an array of single-character strings
        // Example: "Test" → ["T", "e", "s", "t"]
        String[] letterArr = s.split("");

        // Loop through each character in the array
        for (int i = 0; i < letterArr.length; i++) {

            // Repeat the current character (i + 1) times
            // Example: i = 2, "s" → "sss"
            letterArr[i] = letterArr[i].repeat(i + 1);

            // Format the string:
            // - First letter uppercase
            // - Remaining letters lowercase
            // Example: "sss" → "Sss"
            letterArr[i] =
                    letterArr[i].substring(0, 1).toUpperCase() +   // first char uppercase
                            letterArr[i].substring(1).toLowerCase();       // rest lowercase
        }

        // Join all elements with "-" between them
        // Example: ["T", "Ee", "Sss", "Tttt"] → "T-Ee-Sss-Tttt"
        return String.join("-", letterArr);
    }


        public static String accumStream(String s) {

            return IntStream.range(0, s.length()) // loop over indices
                    .mapToObj(i -> {
                        // Get character at position i
                        char c = s.charAt(i);

                        // Build repeated string:
                        // First letter uppercase + repeated lowercase letters
                        return Character.toUpperCase(c) +
                                String.valueOf(Character.toLowerCase(c)).repeat(i);
                    })
                    // Join all parts with "-"
                    .reduce((a, b) -> a + "-" + b)
                    .orElse("");
        }


    public static void main(String[] args) {
        System.out.println(accum("Test")); // Output: T-Ee-Sss-Tttt
    }
}

