class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) return false;
        if (checkSubPath(head, root)) return true;
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }
    
    private boolean checkSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        if (head.val != root.val) return false;
        return checkSubPath(head.next, root.left) || checkSubPath(head.next, root.right);
    }
}