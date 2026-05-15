/*
Return the number (count) of vowels in the given string.

We will consider a, e, i, o, u as vowels for this Kata (but not y).

The input string will only consist of lower case letters and/or spaces.
 */

public class VowelCount {
    public static int getCount(String str) {
        int count = 0; //Counter to track vowels
        for (char item : str.toLowerCase().toCharArray()){ //For loop to iterate through the array and convert to chars
            if(item == 'a' || item == 'e' || item == 'i' || item == 'o' || item == 'u'){ //Check if the current loop contains a vowel
                count++; //If a vowel is found, increase the counter by 1
            }
        }
        return count; //Return counter
    }
}
