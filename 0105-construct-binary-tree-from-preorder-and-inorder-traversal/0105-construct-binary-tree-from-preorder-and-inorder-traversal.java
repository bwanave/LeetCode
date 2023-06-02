/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int rootIdx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i);
        return buildTree(preorder, inorderMap, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, Map<Integer, Integer> inorderMap, int lo, int hi) {
        if (hi < lo) return null;
        TreeNode root = new TreeNode(preorder[rootIdx++]);
        root.left = buildTree(preorder, inorderMap, lo, inorderMap.get(root.val) - 1);
        root.right = buildTree(preorder, inorderMap, inorderMap.get(root.val) + 1, hi);
        return root;
    }
}
