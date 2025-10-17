/**
My friend Theo is a renowned art collector. He has many works in his collection
in very different styles. Some of them are abstract paintings by Piet Mondrian,
who is well known for the very recognizable minimalist and geometric style of his most famous works.

Theo has recently decided to put several Mondrian paintings up for auction,
but he doesn't know what starting price he should put on each painting.

 He had the brilliant idea that the value of each painting will depend on the
number of color regions it has. But Theo is a bit lazy, so he has commissioned
me to create a program that can analyze a painting and automatically determine
the number of color regions that make it up.

Task
Your task is to write a function that is passed a picture to analyze in the form
of a two-dimensional array, and you have to return the number of color regions it has.

Some notes about the input:
-The passed in picture will always be valid (i.e. no null/nil/whatever).

-The passed in picture can however be of any size, even 0x0, so take that
into account (blame Theo, he is not very good at preparing his own data).
Obviously for a 0x0 picture there will be 0 regions, as there is no picture
to analyze!

-The data for the painting to analyze has been simplified by a very powerful
AI to just integer values. You're not forced to interpret colors or anything
complicated. You just need to compare numbers.

-Some of the pictures may not be true Mondrian paintings, as they seems to be
simple solid color rectangles. So don't assume picture size correlates with
region count.

-A picture may have several non-contiguous blocks with the same value. This is
equivalent to the Mondrian painting having several areas painted with the same
color. You should count each of these blocks as an individual region.

-The colored blocks are rectangular. If you find an oddly shaped blob that
is not rectangular, maybe it is composed of more than one adjacent rectangular
regions.

-The output should be just a positive (obviously) number indicating how many
colored regions the painting has. More specifically, what's the minimum amount
of rectangles required to fill the entire picture, where each rectangle consists
of only one color.

For example, this is one of the paintings Theo has sent us:

 9   6
 4  14
It is a simple 2x2 painting with just 4 color regions, so the function should
return 4 for this painting.

Another painting from Theo's collection:

 1  1  1  0
 1  1  2  2
 3  3  1  1
In this one there are 6 regions. That stray 1 in the 1st row, 3rd column is a
single 1x1 region, as it can't be part of any contiguous rectangle, even if it
is adjacent to another region colored also with 1.

 */

public class MondrianRegions {

    public static int countRegions(int[][] picture) {

        //Handle edge cases: if input is null, has no rows, or no columns
        if (picture == null || picture.length == 0 || picture[0].length == 0) {
            return 0;
        }

        int rows = picture.length;      // number of rows in the painting
        int cols = picture[0].length;   // number of columns in the painting

        // This matrix keeps track of which cells we've already counted
        boolean[][] visited = new boolean[rows][cols];

        int regions = 0; // counter for how many regions we find

        //Loop through every cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // If the current cell hasn't been visited yet,
                // it means we've found a new region.
                if (!visited[r][c]) {
                    regions++; // count one new region

                    // Mark all cells belonging to this same rectangular region
                    // so we don't count them again.
                    markRegion(picture, visited, r, c);
                }
            }
        }

        // Return the total number of distinct rectangular regions
        return regions;
    }


    private static void markRegion(int[][] picture, boolean[][] visited, int r, int c) {
        int color = picture[r][c]; // the color value of the current region
        int rows = picture.length;
        int cols = picture[0].length;

        // -----------------------------------------------------------
        // Find how far the region extends horizontally (to the right)
        // -----------------------------------------------------------
        int right = c;
        while (right + 1 < cols && picture[r][right + 1] == color) {
            right++;
        }

        // -----------------------------------------------------------
        // Find how far the region extends vertically (downward)
        // -----------------------------------------------------------
        int down = r;
        boolean expandDown = true;

        // Keep checking rows below as long as the next row has the same color
        // in *all* the columns of the current rectangle width.
        while (expandDown && down + 1 < rows) {
            for (int i = c; i <= right; i++) {
                if (picture[down + 1][i] != color) {
                    // As soon as one cell differs, we stop expanding down.
                    expandDown = false;
                    break;
                }
            }
            // If the whole row matched, include it in the rectangle
            if (expandDown) down++;
        }

        // -----------------------------------------------------------
        // Mark all cells in this rectangle as visited
        // -----------------------------------------------------------
        for (int i = r; i <= down; i++) {
            for (int j = c; j <= right; j++) {
                visited[i][j] = true;
            }
        }
    }

    /**
     * Example usage and test case.
     */
    public static void main(String[] args) {
        // Test 1: Simple 2x2 painting with 4 different colors → 4 regions
        int[][] painting1 = {
                {9, 6},
                {4, 14}
        };
        System.out.println(countRegions(painting1)); // Expected: 4

        // Test 2: Mixed colors and shapes → 6 rectangular regions
        int[][] painting2 = {
                {1, 1, 1, 0},
                {1, 1, 2, 2},
                {3, 3, 1, 1}
        };
        System.out.println(countRegions(painting2)); // Expected: 6

        // Test 3: Empty painting → 0 regions
        int[][] painting3 = {};
        System.out.println(countRegions(painting3)); // Expected: 0
    }
}
