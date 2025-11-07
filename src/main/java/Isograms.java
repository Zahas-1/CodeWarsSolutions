/*
An isogram is a word that has no repeating letters, consecutive or non-consecutive.
Implement a function that determines whether a string that contains only letters is
an isogram. Assume the empty string is an isogram. Ignore letter case.

Example: (Input --> Output)

"Dermatoglyphics" --> true
"aba" --> false
"moOse" --> false (ignore letter case)
 */

import java.util.HashSet;
import java.util.Set;

public class Isograms {

    public static boolean isIsogram(String str) {
        return str.toLowerCase()
                .chars() //Turns the string into a stream of integer values (Unicode code points).
                .distinct()//Removes any duplicate numbers from the stream.
                .count() == str.length(); //Counts how many unique characters remain and compares with the length of the original string
    }

    public static void main(String[] args) {
        System.out.println(isIsogram("Dermatoglyphics")); // true
        System.out.println(isIsogram("aba"));              // false
        System.out.println(isIsogram("moOse"));            // false
        System.out.println(isIsogram(""));                 // true
    }
}