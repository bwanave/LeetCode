class Solution {
    private static final int[][] DIRS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int longestIncreasingPath(int[][] matrix) {
        int length = 0;
        int[][] memo = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) 
                length = Math.max(length, dfs(i, j, matrix, memo));
        }
        return length;
    }
    
    private int dfs(int i, int j, int[][] matrix, int[][] memo) {
        if (memo[i][j] != 0) return memo[i][j];
        
        int path = 0;
        for (int[] dir : DIRS) {
            int row = dir[0] + i, col = dir[1] + j;
            boolean inbound = row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
            if (inbound && matrix[row][col] > matrix[i][j])
                path = Math.max(path, dfs(row, col, matrix, memo));
        }
        memo[i][j] = 1 + path;
        return memo[i][j];
    }
}