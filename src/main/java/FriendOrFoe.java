import java.util.List;        // Import List interface
import java.util.ArrayList;   // Import ArrayList class (implementation of List)

class FriendOrFoe {

    // Method that filters and returns only names with exactly 4 letters
    public static List<String> friend(List<String> x){

        // Create a new list to store valid friend names
        List<String> friends = new ArrayList<>();

        // Loop through each name in the input list
        for (String name : x) {

            // Check if the name has exactly 4 characters
            if (name.length() == 4) {

                // If it does, add it to the friends list
                friends.add(name);
            }
        }

        // Return the filtered list (keeps original order)
        return friends;
    }
}