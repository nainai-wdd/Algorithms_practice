package collection.LeetCode;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class Solution200 {
    private char[][] g;
    private boolean[][] marked;
    private int count = 0;
    private int row;
    private int col;
    public int numIslands(char[][] grid) {
        row = grid.length + 2;
        if(row == 0) return 0;
        col = grid[0].length + 2;
        g = grid;
        marked = new boolean[row][col];
        for (int v = 0; v < row; v++) {
            marked[v][0] = true;
        }
        for (int v = 1; v < col; v++) {
            marked[0][v] = true;
        }
        for (int v = 1; v < row; v++) {
            marked[v][col] = true;
        }
        for (int v = 1; v < col -1; v++) {
            marked[row][v] = true;
        }

        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                if(!marked[i][j] && g[i - 1][j - 1] != '0'){
                    dfs(i,j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j) {
        marked[i][j] = true;
        if (g[i - 1][j - 1] != '0') {
            if(!marked[i - 1][j])   dfs(i - 1, j);
            if(!marked[i][j - 1])   dfs(i, j - 1);
            if(!marked[i + 1][j])   dfs(i + 1, j);
            if(!marked[i][j + 1])   dfs(i, j + 1);
        }
    }

    public static void main(String[] args) {
        char[][] a ={{'1','0','1','1','0','1','1','1','1','1','1','1','0','1','0','1','1','1','1','1'},{'0','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1'},{'0','1','1','1','1','1','0','1','1','0','1','1','1','1','1','1','1','1','0','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0','1'},{'1','1','1','1','0','1','1','1','1','1','1','1','1','0','1','1','1','1','0','1'},{'1','1','1','1','1','0','1','1','1','0','1','1','1','1','1','1','1','1','0','1'},{'1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1'},{'0','1','0','1','1','1','1','1','1','0','0','1','0','1','0','1','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','0','0','0','1','0','1','1','1','1','0','1','0','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','0','1','1'},{'0','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','0','1','1','1','0','1','1','1','1','0','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','1','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}};
        Solution200 solution200 = new Solution200();
        int i = solution200.numIslands(a);
        System.out.println(i);
    }
}