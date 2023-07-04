class Solution {
    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) queue.offer(new int[] {i, j, 0});
                else if (grid[i][j] == 1) freshOranges++;
            }
        }
        
        if (freshOranges == 0) return 0;
        
        while (!queue.isEmpty()) {
            int[] entry = queue.poll();
            for (int[] dir : DIRS) {
                int row = dir[0] + entry[0], col = dir[1] + entry[1];
                boolean inbound = row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
                if (inbound && grid[row][col] == 1) {
                    grid[row][col] = 2;
                    freshOranges--;
                    if (freshOranges == 0) return entry[2] + 1;
                    queue.offer(new int[] {row, col, entry[2] + 1});
                }
            }
        }
        return -1;
    }
}