class Solution {
    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            dfs(i, 0, board);
            dfs(i, board[0].length - 1, board);
        }
        
        for (int i = 0; i < board[0].length; i++) {
            dfs(0, i, board);
            dfs(board.length - 1, i, board);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'T') board[i][j] = 'O';
                else board[i][j] = 'X';
            }
        }
    }

    private void dfs(int i, int j, char[][] board) {
        boolean inbound = i >= 0 && i < board.length && j >= 0 && j < board[0].length;
        if (!inbound) return;
        if (board[i][j] != 'O') return;
        board[i][j] = 'T';
        dfs(i + 1, j, board);
        dfs(i - 1, j, board);
        dfs(i, j + 1, board);
        dfs(i, j - 1, board);
    }
}