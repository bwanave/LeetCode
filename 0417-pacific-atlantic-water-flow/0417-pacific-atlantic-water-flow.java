class Solution {

    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            dfs(i, 0, Integer.MIN_VALUE, heights, pacificReachable);
            dfs(i, cols - 1, Integer.MIN_VALUE, heights, atlanticReachable);
        }

        for (int j = 0; j < cols; j++) {
            dfs(0, j, Integer.MIN_VALUE, heights, pacificReachable);
            dfs(rows - 1, j, Integer.MIN_VALUE, heights, atlanticReachable);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) result.add(List.of(i, j));
            }
        }
        return result;
    }

    private void dfs(int i, int j, int prevHeight, int[][] heights, boolean[][] oceanReachable) {
        boolean inbound = i >= 0 && i < heights.length && j >= 0 && j < heights[0].length;
        if (!inbound) return;
        if (oceanReachable[i][j]) return;
        if (heights[i][j] < prevHeight) return;
        oceanReachable[i][j] = true;
        for (int[] dir : DIRS) {
            int row = i + dir[0];
            int col = j + dir[1];
            dfs(row, col, heights[i][j], heights, oceanReachable);
        }
    }
}
