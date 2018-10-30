/*
 An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, 
plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. 
Replace the color of all of the aforementioned pixels with the newColor.
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.

At the end, return the modified image. 
*/

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int [][] newColor = new int[image.length][image[0].length];
        int color = image[sr][sc];
        if (image[i][j] != newColor) {
            dfs(newColor, i, j, color);
        }
        return image;
    }
    private void dfs(int[][] image, int i, int j, int color, int newColor) {
        if (i >= 0 && i < image.length && j >= 0 && j < image[0].length && image[i][j] == color) {
            image[i][j] = newColor;
            dfs(image, i, j-1, color, newColor); 
            dfs(image, i, j+1, color, newColor); 
            dfs(image, i-1, j, color, newColor); 
            dfs(image, i+1, j, color, newColor); 
        }
    }
}