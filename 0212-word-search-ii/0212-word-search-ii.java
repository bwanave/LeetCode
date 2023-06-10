class Solution {
    
    private static final int[][] DIRS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        Arrays.stream(words).forEach(trie::insert);
        
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, result, trie.root);
            }
        }
        return result;
    }
    
    private void dfs(int i, int j, char[][] board, List<String> result, Node curr) {
        boolean inbound = i >= 0 && i < board.length && j >= 0 && j < board[0].length;
        if (!inbound) return;
        if (board[i][j] == '#') return;
        if (curr.children[board[i][j] - 'a'] == null) return;
        
        curr = curr.children[board[i][j] - 'a'];
        if (curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }
        
        char c = board[i][j];
        board[i][j] = '#';
        for (int[] dir : DIRS) {
            int row = i + dir[0], col = j + dir[1];
            dfs(row, col, board, result, curr);
        }
        
        board[i][j] = c;
    }
}

class Trie {
    Node root = new Node();
    
    public void insert(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) curr.children[c - 'a'] = new Node();
            curr = curr.children[c - 'a'];
        }
        curr.word = word;
    }
}

class Node {
    Node[] children = new Node[26];
    String word;
}