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

    public int maxPathSum(TreeNode root) {
        return findMaxPathSum(root)[1];
    }

    private int[] findMaxPathSum(TreeNode root) {
        if (root == null) return new int[] { 0, Integer.MIN_VALUE }; // pathSum, maxPathSum
        int[] left = findMaxPathSum(root.left);
        int[] right = findMaxPathSum(root.right);
        int pathSum = root.val + Math.max(0, Math.max(left[0], right[0]));
        int maxPathSum = Math.max(0, left[0]) + root.val + Math.max(0, right[0]);
        maxPathSum = Math.max(maxPathSum, Math.max(left[1], right[1]));
        return new int[] { pathSum, maxPathSum };
    }
}
