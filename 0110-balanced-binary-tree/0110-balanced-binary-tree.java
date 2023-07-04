class Solution {
    public boolean isBalanced(TreeNode root) {
        return getTreeInfo(root).balanced;
    }

    private TreeInfo getTreeInfo(TreeNode root) {
        if (root == null) return new TreeInfo(-1, true);
        TreeInfo left = getTreeInfo(root.left);
        if (!left.balanced) return left;
        TreeInfo right = getTreeInfo(root.right);
        if (!right.balanced) return right;
        return new TreeInfo(Math.max(left.height, right.height) + 1, Math.abs(left.height - right.height) <= 1);
    }
}

class TreeInfo {
    int height;
    boolean balanced;
    
    TreeInfo(int height, boolean balanced) {
        this.height = height;
        this.balanced = balanced;
    }
}