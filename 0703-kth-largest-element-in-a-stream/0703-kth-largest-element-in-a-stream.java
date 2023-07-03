// // Approach: Priority queue
// class KthLargest {

//     private final PriorityQueue<Integer> pq = new PriorityQueue<>();
//     private final int k;

//     // time: nlogn
//     public KthLargest(int k, int[] nums) {
//         this.k = k;
//         for (int num : nums) {
//             pq.offer(num);
//             if (pq.size() > k) pq.poll();
//         }
//     }
    
//     // time: logk
//     public int add(int val) {
//         pq.offer(val);
//         if (pq.size() > k) pq.poll();
//         return pq.peek();
//     }
// }

// Approach: BST
class KthLargest {

    private final BST bst = new BST();
    private final int k;

    // time: nlogn
    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) 
            bst.insert(num);
    }
    
    // time: logn
    public int add(int val) {
        bst.insert(val);
        return bst.searchLargestKth(k);
    }
}

class BST {
    static class Node {
        int val;
        Node left, right;
        int count;

        Node(int val) {
            this.val = val;
            this.count = 1;
        }
    }

    private Node root;

    public void insert(int val) {
        this.root = insert(val, root);
    }

    private Node insert(int val, Node root) {
        if (root == null) return new Node(val);
        
        if (val < root.val) root.left = insert(val, root.left);
        else root.right = insert(val, root.right);
        root.count++;
        return root;
    }

    public int searchLargestKth(int k) {
        return searchLargestKth(k, root).val;
    }

    private Node searchLargestKth(int k, Node root) {
        int rightCount = root.right != null ? root.right.count : 0;
        if (k == rightCount + 1) return root;
        else if (k <= rightCount) return searchLargestKth(k, root.right);
        else return searchLargestKth(k - (rightCount + 1), root.left);
    }
}











