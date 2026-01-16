/*
The Morse code encodes every character as a sequence of "dots" and "dashes".
For example, the letter A is coded as ·−, letter Q is coded as −−·−, and digit
1 is coded as ·−−−−. The Morse code is case-insensitive, traditionally capital
letters are used. When the message is written in Morse code, a single space is
used to separate the character codes and 3 spaces are used to separate words.
For example, the message HEY JUDE in Morse code is ···· · −·−−   ·−−− ··− −·· ·.

NOTE: Extra spaces before or after the code have no meaning and should be ignored.

In addition to letters, digits and some punctuation, there are some special
service codes, the most notorious of those is the international distress signal SOS,
that is coded as ···−−−···. These special codes are treated as single special
characters, and usually are transmitted as separate words.

Your task is to implement a function that would take the morse code as input and
return a decoded human-readable string.

For example:

MorseCodeDecoder.decode(".... . -.--   .--- ..- -.. .")
//should return "HEY JUDE"
NOTE: For coding purposes you have to use ASCII characters . and -, not Unicode
characters.

The Morse code table is preloaded for you as a dictionary, feel free to use it:

Java: MorseCode.get(".--")

All the test strings would contain valid Morse code, so you may skip
checking for errors and exceptions.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MorseCodeDecoder {
    public static String decode(String morseCode) {
        // 1. .trim() removes leading/trailing spaces as required by the task.
        // 2. Arrays.stream(...) splits the message into individual Morse "words".
        return Arrays.stream(morseCode.trim().split("   "))
                .map(word -> Arrays.stream(word.split(" ")) // 3. Split each word into individual Morse letters
                        .map(MorseCode::get)               // 4. Translate each Morse code letter to English
                        .collect(Collectors.joining()))    // 5. Join letters back together into a word
                .collect(Collectors.joining(" "));         // 6. Join words together with a single space
    }
}

class MorseCode {
    // A static map to store the Morse-to-English translations
    private static final Map<String, String> CODE_MAP = new HashMap<>();

        static {
            CODE_MAP.put(".-", "A");
            CODE_MAP.put("-...", "B");
            CODE_MAP.put("-.-.", "C");
            CODE_MAP.put("-..", "D");
            CODE_MAP.put(".", "E");
            CODE_MAP.put("..-.", "F");
            CODE_MAP.put("--.", "G");
            CODE_MAP.put("....", "H");
            CODE_MAP.put("..", "I");
            CODE_MAP.put(".---", "J");
            CODE_MAP.put("-.-", "K");
            CODE_MAP.put(".-..", "L");
            CODE_MAP.put("--", "M");
            CODE_MAP.put("-.", "N");
            CODE_MAP.put("---", "O");
            CODE_MAP.put(".--.", "P");
            CODE_MAP.put("--.-", "Q");
            CODE_MAP.put(".-.", "R");
            CODE_MAP.put("...", "S");
            CODE_MAP.put("-", "T");
            CODE_MAP.put("..-", "U");
            CODE_MAP.put("...-", "V");
            CODE_MAP.put(".--", "W");
            CODE_MAP.put("-..-", "X");
            CODE_MAP.put("-.--", "Y");
            CODE_MAP.put("--..", "Z");
            CODE_MAP.put("-----", "0");
            CODE_MAP.put(".----", "1");
            CODE_MAP.put("..---", "2");
            CODE_MAP.put("...--", "3");
            CODE_MAP.put("....-", "4");
            CODE_MAP.put(".....", "5");
            CODE_MAP.put("-....", "6");
            CODE_MAP.put("--...", "7");
            CODE_MAP.put("---..", "8");
            CODE_MAP.put("----.", "9");
            CODE_MAP.put("...---...", "SOS");
        }

        public static String get(String morse) {
            return CODE_MAP.get(morse);
        }
    }
