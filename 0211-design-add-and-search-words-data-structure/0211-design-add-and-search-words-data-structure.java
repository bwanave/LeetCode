class WordDictionary {
    private final Node root = new Node();

    public void addWord(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) curr.children[c - 'a'] = new Node();
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int i, Node curr) {
        for (; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                for (Node child : curr.children) {
                    if (child != null && search(word, i + 1, child)) return true;
                }
                return false;
            }
            if (curr.children[c - 'a'] == null) return false;
            curr = curr.children[c - 'a'];
        }
        return curr.isWord;
    }
}

class Node {
    Node[] children = new Node[26];
    boolean isWord;
}
