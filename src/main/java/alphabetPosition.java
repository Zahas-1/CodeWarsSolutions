/*
Welcome.

In this kata you are required to, given a string, replace every letter with its position in the alphabet.

If anything in the text isn't a letter, ignore it and don't return it.

"a" = 1, "b" = 2, etc.

Example
Input = "The sunset sets at twelve o' clock."
Output = "20 8 5 19 21 14 19 5 20 19 5 20 19 1 20 20 23 5 12 22 5 15 3 12 15 3 11"
 */


import java.util.stream.Collectors;

public class alphabetPosition {
    static String alphabetPosition(String text) {
        StringBuilder numbersOutput = new StringBuilder(); //Stringbuilder to store result
        for(char item : text.toCharArray()){ //Convert to char array to work with unicode characters
            if(Character.isAlphabetic(item)){ //Check if the current character is a letter
                if(Character.isUpperCase(item)){ //Check for upper case
                    numbersOutput.append(item - 'A' + 1); //If it's upper case, subtract an 'A' and then plus 1
                }
                if(Character.isLowerCase(item)){ //Check for lower case
                    numbersOutput.append(item - 'a' + 1); //If it's lower case, subtract from 'a' and then plus 1
                }
                numbersOutput.append(" "); //Append the Stringbuilder to include a blank space after the characters.
            }
        }
        return numbersOutput.toString().trim(); //Convert the Stringbuilder to a String and remove the last blank space.
    }

//Stream solution of the above
    static String alphabetPositionStream(String text) {
        return text.chars()
                .filter(Character::isLetter)
                .map(Character::toLowerCase)
                .map(c -> c - 'a' + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}
