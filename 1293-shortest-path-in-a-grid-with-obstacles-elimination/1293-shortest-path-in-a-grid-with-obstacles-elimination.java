class Solution {
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestPath(int[][] grid, int k) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 0}); // [row, col, obstacles, level];

        int[][] visited = new int[grid.length][grid[0].length]; // Stores the value of encountered obstacles when visited the cell
        for (int[] v : visited)
            Arrays.fill(v, Integer.MAX_VALUE);
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] entry = queue.poll();
            int row = entry[0];
            int col = entry[1];
            int obstacles = entry[2];
            int level = entry[3];

            if (row == grid.length - 1 && col == grid[0].length - 1)
                return level;

            for (int[] dir : DIRS) {
                int nextRow = row + dir[0], nextCol = col + dir[1];
                boolean inbound = nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length;
                if (inbound) {
                    int newObstacles = obstacles + grid[nextRow][nextCol];
                    if (newObstacles <= k && newObstacles < visited[nextRow][nextCol]) {
                        visited[nextRow][nextCol] = newObstacles;
                        queue.offer(new int[]{nextRow, nextCol, newObstacles, level + 1});
                    }
                }
            }
        }
        return -1;
    }
}