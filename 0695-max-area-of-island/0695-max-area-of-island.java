class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxSize = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maxSize = Math.max(maxSize, dfs(i, j, grid));
            }
        }
        return maxSize;
    }

    private int dfs(int i, int j, int[][] grid) {
        boolean inbound = i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
        if (!inbound) return 0;
        if (grid[i][j] == 0) return 0;
        grid[i][j] = 0;
        
        int size = 1;
        size += dfs(i + 1, j, grid);
        size += dfs(i - 1, j, grid);
        size += dfs(i, j + 1, grid);
        size += dfs(i, j - 1, grid);
        return size;
    }
}